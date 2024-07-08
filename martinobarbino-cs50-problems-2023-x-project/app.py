import datetime
from flask import Flask, render_template, request

# Set up flask
app = Flask(__name__)


@app.route("/", methods=["GET", "POST"])
def index():

    # Case for submission of form
    if request.method == "POST":

        # Catch cases for form failure
        if not request.form.get("occasion"):
            return render_template("error.html")

        elif not request.form.get("level"):
            return render_template("error.html")

        elif not request.form.get("weather"):
            return render_template("error.html")

        else:

            level = "!" * int(request.form.get("level"))

            # Motivation for weekend
            dayNumber = datetime.datetime.today().weekday()
            if dayNumber >= 5:
                return render_template("weekendquote.html", occasion=request.form.get("occasion"), level=level, weather=request.form.get("weather"))

            # All other days
            elif request.form.get("occasion") == "meaning":
                return render_template("meaning.html", level=level, weather=request.form.get("weather"))

            else:
                return render_template("quote.html", occasion=request.form.get("occasion"), level=level, weather=request.form.get("weather"))

    return render_template("index.html")
