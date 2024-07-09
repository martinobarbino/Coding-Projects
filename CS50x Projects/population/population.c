#include <cs50.h>
#include <stdio.h>

int main(void)
{
    // TODO: Prompt for start size
    int start;
    do
    {
        start = get_int("How many emus were there at the start? ");

        if (start < 9)

            printf("That's too few emus! Start with more emus.\n");
    }
    while (start < 9);
    // TODO: Prompt for end size
    int end;
    do
    {
        end = get_int("How many emus were there at the end? ");

        if (end < start)

            printf("There can't be less emus at the end than at the start! End with more emus.\n");
    }
    while (end < start);
    // TODO: Calculate number of years until we reach threshold
    int years = 0;
    while (start < end)
    {
        start = start + (start / 3) - (start / 4);
        years++;
    }

    // TODO: Print number of years
    printf("Years: %i\n", years);
}
