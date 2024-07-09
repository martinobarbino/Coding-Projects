#include <cs50.h>
#include <ctype.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

int count_letters(string text);
int count_words(string text);
int count_sentences(string text);

int main(void)
{
    string text = get_string("Text: ");
    float letters = count_letters(text);
    float words = count_words(text);
    float sentences = count_sentences(text);
    // Equation for index
    float L = (letters / words) * 100;
    float S = (sentences / words) * 100;
    float index = (.0588 * L) - (.296 * S) - 15.8;
    int roundedindex = round(index);
    // Prints result in correct format
    if (roundedindex < 1)
    {
        printf("Before Grade 1\n");
    }
    if (roundedindex >= 16)
    {
        printf("Grade 16+\n");
    }
    if (roundedindex > 1 && roundedindex < 16)
    {
        printf("Grade %i\n", roundedindex);
    }
}

int count_letters(string text)
{
    // Use ticker to go through each character of text. Ticker stops when it reaches the end of text.
    int letters = 0;
    for (int ticker = 0; ticker < strlen(text); ticker++)
    {
        // Checks if character is alphabetical and stores the count of alphabetical characters in variable 'letters'
        if (isalpha(text[ticker]) != 0)
        {
            letters++;
        }
    }
    return letters;
}

int count_words(string text)
{
    // Same type of ticker as with letters
    int words = 1;
    for (int ticker = 0; ticker < strlen(text); ticker++)
    {
        // Checks if there's a space, which would indicate a new word.
        // The count starts at one since there would be no space for the first word.
        if (isspace(text[ticker]) != 0)
        {
            words++;
        }
    }
    return words;
}

int count_sentences(string text)
{
    // Again, same type of ticker.
    int sentences = 0;
    for (int ticker = 0; ticker <= strlen(text); ticker++)
    {
        // When ticker detects sentence ending characters, it counts that as a sentence
        // Stores result in variable 'sentences'
        if (text[ticker] == '.' || text[ticker] == '!' || text[ticker] == '?')
        {
            sentences++;
        }
    }
    return sentences;
}