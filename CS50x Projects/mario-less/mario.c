#include <cs50.h>
#include <stdio.h>

int main(void)
{
    int usrinput;
    do
    {
        usrinput = get_int("Height: ");
    }
    while (usrinput < 1 || usrinput > 8);
    // Declare rows which is equal to height-1 because the first row does not requre to start a new line
    // Nested loop which prints hashes. Each iteration it adds one to print by adding one with the rows
    // The outer loop prints new lines and stops at the integer for rows
    int rows = usrinput - 1;
    for (int ticker = 0; ticker <= rows; ticker++)
    {
        // Print spaces in a way that it prints usrinput - 1 and ticks down
        for (int ticker3 = usrinput - 1; ticker3 > ticker; ticker3--)
        {
            printf(" ");
        }
        for (int ticker2 = 0; ticker2 <= ticker; ticker2++)
        {
            printf("#");
        }
        printf("\n");
    }
}
