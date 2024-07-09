# Import necessary functions
from cs50 import get_string


def main():
    # Call functions to get the values for the text processing equation
    t = get_string("Text: ")
    l = count_letters(t)
    print(l)
    w = count_words(t)
    print(w)
    s = count_sentences(t)
    print(s)
    # Text processing equation
    L = (l / w) * 100
    S = (s / w) * 100
    index = round((0.0588 * L) - (0.296 * S) - 15.8)
    # Print result based on specification
    if index < 1:
        print("Before Grade 1")

    if index >= 16:
        print("Grade 16+")

    if index > 1 and index < 16:
        print(f"Grade {index}")


def count_letters(t):
    letters = 0
    for i in t:
        if i.isalpha():
            letters += 1
    return letters


def count_words(t):
    words = 1
    for i in t:
        if i == " ":
            words += 1
    return words


def count_sentences(t):
    sentences = 0
    for i in t:
        if i in (".", "!", "?"):
            sentences += 1
    return sentences


main()
