# import libraries
from cs50 import get_float


def main():
    cents = get_cents()
    quarters = calculate_quarters(cents)
    cents = cents - quarters * 25
    dimes = calculate_dimes(cents)
    cents = cents - dimes * 10
    nickels = calculate_nickels(cents)
    cents = cents - nickels * 5
    pennies = calculate_pennies(cents)
    cents = cents - pennies * 1
    coins = int(quarters + dimes + nickels + pennies)
    print(coins)


def get_cents():
    while True:
        cents = get_float("Change owed: ")
        cents = int(cents * 100)
        if cents >= 0:
            return cents


def calculate_quarters(cents):
    # Take cents and divide it by 25, returning the result.
    quarters = cents // 25
    return quarters


def calculate_dimes(cents):
    dimes = cents // 10
    return dimes


def calculate_nickels(cents):
    nickels = cents // 5
    return nickels


def calculate_pennies(cents):
    pennies = cents // 1
    return pennies


main()
