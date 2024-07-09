# Import get_int
from cs50 import get_int


def main():
    # Gets user input for height
    h = get_height()
    # For loop takes height and prints pyramid according to height
    # Goes through height and makes index of rows
    for row in range(h):
        # Spaces = height - (row# + 1)
        print(" " * (h - (row + 1)), end="")
        # Hases = row # + 1
        print("#" * (row + 1))


def get_height():
    # Functions like C do/while
    while True:
        h = get_int("Height: ")
        if h >= 1 and h <= 8:
            return h


# Must call main
main()
