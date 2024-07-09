// Implements a dictionary's functionality

#include "dictionary.h"
#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// TODO: Choose number of buckets in hash table
const unsigned int N = 729;

// Character array for analyzing words
char w[45];

// Size variable
int s = 0;

// Hash table
node *table[N];

// Returns true if word is in dictionary, else false
bool check(const char *word)
{
    // Hash word to obtain a hash value
    int i = hash(word);
    // Access linked list at that index in the hash table, traverse list looking for match
    node *c = NULL;
    c = table[i];
    // Loop for going over each word, stops at end of list
    while (c != NULL)
    {
        // Checks for word find
        if (strcasecmp(word, c->word) == 0)
        {
            return true;
        }
        // Goes to next item if no match
        else
        {
            c = c->next;
        }
    }
    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)
{
    int fl = 0;
    int sl = 0;
    // Take first character and give it a number between 1-26 based on alphabet
    if (isupper(word[0]))
    {
        // Minus word's value by 65, the start of uppercase alphabet
        fl = ((word[0]) - 65);
    }
    if (islower(word[0]))
    {
        // Minus word's value by 97, the start of lowercase alphabet
        fl = ((word[0]) - 97);
    }
    if (ispunct(word[0]))
    {
        // Gives all punctuation value of 27 since there isn't that many words with punct
        fl = 27;
    }
    // Same process for second character as first
    if (isupper(word[1]))
    {
        sl = ((word[1]) - 65);
    }
    if (islower(word[1]))
    {
        sl = ((word[1]) - 97);
    }
    if (ispunct(word[1]))
    {
        sl = 27;
    }
    // Multiply result of first two letters for bucket index
    int i = (fl * sl);
    return i;
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    // Open dictionary
    FILE *x = fopen(dictionary, "r");
    if (x == NULL)
    {
        return false;
    }
    // Read strings from file
    // Needs to be a loop that continues to the end of the file.
    while (fscanf(x, "%s", w) != EOF)
    {
        // Create node for word
        node *n = malloc(sizeof(node));
        if (n == NULL)
        {
            return false;
        }
        else
        {
            // Copy string you read from the file into the node
            strcpy(n->word, w);
            // Get hash index for the word
            int i = hash(n->word);
            // Insert node into the table[i] linked list
            n->next = table[i];
            table[i] = n;
            s++;
        }
    }
    // Close file after reading
    fclose(x);
    return true;
}

// Returns number of words in dictionary if loaded, else 0 if not yet loaded
unsigned int size(void)
{
    // s is incremented every time a new node is created
    return s;
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    // For loop goes through all buckets of linked list
    for (int i = 0; i <= N; i++)
    {
        // Handle base case
        // Set new nodes for going through nodes
        node *c = table[i];
        node *tmp = NULL;
        // Stops at end of list
        while (c != NULL)
        {
            // Frees each node that pointer previously pointed at as cursor goes through whole list
            tmp = c;
            c = c->next;
            free(tmp);
        }
    }
    return true;
}
