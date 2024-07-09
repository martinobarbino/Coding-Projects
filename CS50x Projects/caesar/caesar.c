#include <cs50.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

bool only_digits(string argv1);
char rotate(char plainchar, int k);

int main(int argc, string argv[])
{
    string argv1 = argv[1];
    // Command line argument only accepts one argument and digits
    if (argc != 2 || only_digits(argv1) == true)
    {
        printf("Usage ./caesar key\n");
        return 1;
    }
    // Converts argv1 to integer
    int k = atoi(argv1);
    // Prompts user for the plaintext
    string plaintext = get_string("plaintext:  ");
    // Now take k and apply a function that takes characters from the plaintext string and adds k to it
    printf("ciphertext: ");
    for (int ticker = 0; ticker < strlen(plaintext); ticker++)
    {
        char cipherchar = rotate(plaintext[ticker], k);
        printf("%c", cipherchar);
    }
    printf("\n");
    return 0;
}

// Function for checking command line argument for only digits
bool only_digits(string argv1)
{
    for (int ticker = 0; ticker < strlen(argv1); ticker++)
    {
        if (isdigit(argv1[ticker]) == 0)
        {
            return true;
        }
    }
    return false;
}

char rotate(char plainchar, int k)
{
    // Function roughly works like plainchar + k = cipherchar
    // Problem is, if you do it this basic, the text will not wrap and spaces/punctuation will be changed which we don't want.
    // Have the function governed by if statements that detect uppercase and lowercase letters then apply the proper functions to
    // them.
    if (isupper(plainchar))
    {
        // Minus plainchar's value by 65, the start of uppercase alphabet
        // Add % 26 of k to the result. The remainder function makes it so the text 'wraps'
        // Finally, add the ascii value back to reconvert it.
        return ((plainchar - 65 + k) % 26) + 65;
    }
    if (islower(plainchar))
    {
        return ((plainchar - 97 + k) % 26) + 97;
    }
    else
    {
        return plainchar;
    }
    return 0;
}
