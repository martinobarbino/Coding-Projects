#include <cs50.h>
#include <stdio.h>
#include <string.h>

// Max number of candidates
#define MAX 9

// Candidates have name and vote count
typedef struct
{
    string name;
    int votes;
} candidate;

// Array of candidates
candidate candidates[MAX];

// Number of candidates
int candidate_count;

// Function prototypes
bool vote(string name);
void print_winner(void);

int main(int argc, string argv[])
{
    // Check for invalid usage
    if (argc < 2)
    {
        printf("Usage: plurality [candidate ...]\n");
        return 1;
    }

    // Populate array of candidates
    candidate_count = argc - 1;
    if (candidate_count > MAX)
    {
        printf("Maximum number of candidates is %i\n", MAX);
        return 2;
    }
    for (int i = 0; i < candidate_count; i++)
    {
        candidates[i].name = argv[i + 1];
        candidates[i].votes = 0;
    }

    int voter_count = get_int("Number of voters: ");

    // Loop over all voters
    for (int i = 0; i < voter_count; i++)
    {
        string name = get_string("Vote: ");

        // Check for invalid vote
        if (!vote(name))
        {
            printf("Invalid vote.\n");
        }
    }

    // Display winner of election
    print_winner();
}

// Update vote totals given a new vote
bool vote(string name)
{
    // loop starting at zero, stopping at candidate count, and moving up 1
    for (int i = 0; i < candidate_count; i++)
    {
        // in the for loop:
        // if string name = candidate[i].name:
        // candidate[i].votes++ and return true
        if (strcmp(candidates[i].name, name) == 0)
        {
            candidates[i].votes++;
            return true;
        }
    }
    // function returns false if true hasn't been triggered already
    return false;
}

// Print the winner (or winners) of the election
void print_winner(void)
{
    // for loop that that starts at 0 and stops at candidate count
    // in the for loop, the first candidate is compared to the next
    // if the candidate has more votes than the next, the candidate's vote count is identified as the winning vote count
    // once the loop ends, have the function print the name of the candidate who had the winning vote count
    int wv = candidates[0].votes;
    for (int i = 1; i < candidate_count; i++)
    {
        if (candidates[i].votes > wv)
        {
            wv = candidates[i].votes;
        }
    }
    for (int j = 0; j < candidate_count; j++)
    {
        if (candidates[j].votes == wv)
        {
            printf("%s\n", candidates[j].name);
        }
    }
    return;
}