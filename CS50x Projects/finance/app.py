import os

from cs50 import SQL
from flask import Flask, flash, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash

from helpers import apology, login_required, lookup, usd

# Configure application
app = Flask(__name__)

# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")


@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
@login_required
def index():
    """Show portfolio of stocks"""
    # Select list of the unique stock names that the user has purchased, this is leftmost column
    stockNames = db.execute(
        "SELECT stock FROM holdings WHERE user_id = ?", session["user_id"]
    )
    totalHoldings = 0

    # Case for if holdings is not already populated
    if len(stockNames) == 0:
        return render_template("welcome.html")

    else:
        # Select data about each particular stock in the list, add to dict
        for i in range(len(stockNames)):
            result = db.execute(
                "SELECT shares FROM holdings WHERE stock = ? AND user_id = ?",
                stockNames[i]["stock"],
                session["user_id"],
            )
            stockNames[i]["totalShares"] = result[0]["shares"]
            result = lookup(stockNames[i]["stock"])
            stockNames[i]["prices"] = usd(result["price"])
            stockNames[i]["holdings"] = usd(
                stockNames[i]["totalShares"] * result["price"]
            )
            totalHoldings += stockNames[i]["totalShares"] * result["price"]

        # Find user balance data and plug into variables
        cashBalance = db.execute("SELECT * FROM users WHERE id = ?", session["user_id"])
        grandTotal = totalHoldings + cashBalance[0]["cash"]
        return render_template(
            "index.html",
            stockNames=stockNames,
            cashBalance=usd(cashBalance[0]["cash"]),
            grandTotal=usd(grandTotal),
        )


@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    """Buy shares of stock"""

    if request.method == "POST":
        # Check if inputs are blank
        if not request.form.get("symbol"):
            return apology("invalid stock symbol", 400)

        if not request.form.get("shares"):
            return apology("invalid number of shares", 400)

        # Check if symbol equals a real stock
        if lookup(request.form.get("symbol")) == None:
            return apology("invalid stock symbol", 400)

        # Check if shares is an integer
        if request.form.get("shares").isnumeric() == False:
            return apology("invalid number of shares", 400)

        # Check if number of shares is negative
        if int(request.form.get("shares")) < 0:
            return apology("invalid number of shares", 403)

        else:
            # Define user's money available
            userMoney = db.execute(
                "SELECT * FROM users WHERE id = ?", session["user_id"]
            )

            # Define purchase price
            stockData = lookup(request.form.get("symbol"))
            purchasePrice = stockData["price"] * int(request.form.get("shares"))

            # Case if user has insufficient funds
            if (userMoney[0]["cash"] - purchasePrice) < 0:
                return apology("you cannot afford this purchase", 403)

            # Adjust database based on purchase if purchase was successful
            elif (userMoney[0]["cash"] - purchasePrice) >= 0:
                # Redefine user's balance based on purchase
                newBalance = userMoney[0]["cash"] - purchasePrice
                db.execute(
                    "UPDATE users SET cash = ? WHERE id = ?",
                    newBalance,
                    session["user_id"],
                )

                # Insert purchase data into purchase table
                db.execute(
                    "INSERT INTO transactions(user_id, stock, price, shares, transaction_type) VALUES(?, ?, ?, ?, ?)",
                    session["user_id"],
                    request.form.get("symbol"),
                    stockData["price"],
                    int(request.form.get("shares")),
                    "Purchase",
                )

                # Update user's holdings data
                # Case if the stock is not already in the holdings table not working. Need to some way to do this properly...
                presentShares = db.execute(
                    "SELECT shares FROM holdings WHERE stock = ? AND user_id = ?",
                    request.form.get("symbol"),
                    session["user_id"],
                )
                if len(presentShares) == 0:
                    db.execute(
                        "INSERT INTO holdings(stock, shares, user_id) VALUES(?, ?, ?)",
                        request.form.get("symbol"),
                        int(request.form.get("shares")),
                        session["user_id"],
                    )

                else:
                    db.execute(
                        "UPDATE holdings SET shares = ? WHERE user_id = ? AND stock = ?",
                        presentShares[0]["shares"] + int(request.form.get("shares")),
                        session["user_id"],
                        request.form.get("symbol"),
                    )

            # Case if purchase completed successfully
            return redirect("/")

    return render_template("buy.html")


@app.route("/history")
@login_required
def history():
    """Show history of transactions"""

    # Query for transaction data and plug into html
    transactionData = db.execute(
        "SELECT * FROM transactions WHERE user_id = ?", session["user_id"]
    )
    return render_template("history.html", transactionData=transactionData)


@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id"]

        # Redirect user to home page
        return redirect("/")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/password", methods=["GET", "POST"])
@login_required
def password():
    """Give user ability to change password"""

    if request.method == "POST":
        # Case if password not submitted
        if not request.form.get("password"):
            return apology("must provide password", 400)

        elif not request.form.get("confirmation"):
            return apology("must confirm password", 400)

        # Check if password matches confirmation
        elif request.form.get("password") != request.form.get("confirmation"):
            return apology("password must match confirmation password", 400)

        else:
            # Update password database based on new password
            db.execute(
                "UPDATE users SET hash = ? WHERE id = ?",
                generate_password_hash(request.form.get("password")),
                session["user_id"],
            )
            return redirect("/")

    return render_template("password.html")


@app.route("/quote", methods=["GET", "POST"])
@login_required
def quote():
    """Get stock quote."""

    # Triggers when form is submitted
    if request.method == "POST":
        # Case for user not inputting value
        if not request.form.get("symbol"):
            return apology("must enter a stock symbol", 400)

        # Searches based on user inputted key
        result = lookup(request.form.get("symbol"))

        # Case for if symbol turns up no results
        if result is None:
            return apology("no results", 400)

        else:
            # Returns page showing results
            return render_template(
                "quoted.html", symbol=result["symbol"], price=usd(result["price"])
            )

    # Loads quote template
    return render_template("quote.html")


@app.route("/register", methods=["GET", "POST"])
def register():
    """Register user"""

    # Forget possible previous user id
    session.clear()

    # User submitted form
    if request.method == "POST":
        test = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Case if username not submitted
        if not request.form.get("username"):
            return apology("must provide username", 400)

        # Check if username matches another user's password
        elif len(test) != 0:
            return apology("username already in use", 400)

        # Case if password not submitted
        elif not request.form.get("password"):
            return apology("must provide password", 400)

        elif not request.form.get("confirmation"):
            return apology("must confirm password", 400)

        # Check if password matches confirmation
        elif request.form.get("password") != request.form.get("confirmation"):
            return apology("password must match confirmation password", 400)

        # Register user into database
        db.execute(
            "INSERT INTO users (username, hash, cash) VALUES(?, ?, ?)",
            request.form.get("username"),
            generate_password_hash(
                request.form.get("password"), method="pbkdf2", salt_length=16
            ),
            10000,
        )

        # Login screen
        return render_template("login.html")

    # render the html file
    return render_template("register.html")


@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    """Sell shares of stock"""

    # Populate drop down menu for sellable stocks
    stockNames = db.execute(
        "SELECT stock FROM holdings WHERE user_id = ?", session["user_id"]
    )

    # Form is submitted
    if request.method == "POST":
        # Case if stock is not entered
        if not request.form.get("symbol"):
            return apology("must select a stock", 400)

        if request.form.get("shares").isnumeric() == False:
            return apology("invalid number of shares", 400)

        # Case if shares is negative
        if int(request.form.get("shares")) <= 0:
            return apology("invalid number of shares", 400)

        # Case if stock is not owned by user
        presentShares = db.execute(
            "SELECT shares FROM holdings WHERE stock = ? AND user_id = ?",
            request.form.get("symbol"),
            session["user_id"],
        )
        if len(presentShares) == 0:
            return apology("invalid stock sale", 400)

        # Case if user does not own enough shares for sale
        elif int(request.form.get("shares")) > int(presentShares[0]["shares"]):
            return apology("you do not have enough shares for this sale", 400)

        # Update holdings and transactions based on sale
        else:
            # Update user balance
            # Define user's money available
            userMoney = db.execute(
                "SELECT * FROM users WHERE id = ?", session["user_id"]
            )

            # Define purchase price
            stockData = lookup(request.form.get("symbol"))
            saleAmt = stockData["price"] * int(request.form.get("shares"))

            # Redefine user's balance based on purchase
            newBalance = userMoney[0]["cash"] + saleAmt
            db.execute(
                "UPDATE users SET cash = ? WHERE id = ?", newBalance, session["user_id"]
            )

            # Update transactions
            db.execute(
                "INSERT INTO transactions(user_id, stock, price, shares, transaction_type) VALUES(?, ?, ?, ?, ?)",
                session["user_id"],
                request.form.get("symbol"),
                stockData["price"],
                int(request.form.get("shares")),
                "Sale",
            )

            # Update holdings
            db.execute(
                "UPDATE holdings SET shares = ? WHERE user_id = ? AND stock = ?",
                presentShares[0]["shares"] - int(request.form.get("shares")),
                session["user_id"],
                request.form.get("symbol"),
            )

            if presentShares[0]["shares"] - int(request.form.get("shares")) == 0:
                db.execute(
                    "DELETE FROM holdings WHERE stock = ?", request.form.get("symbol")
                )

        return redirect("/")

    return render_template("sell.html", stockNames=stockNames)
