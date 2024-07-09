#include <cs50.h>
#include <stdio.h>
#include <string.h>

const int BITS_IN_BYTE = 8;

void print_bulb(int bit);

int main(void)
{
    // Program prompts user for text
    string msg = get_string("Message: ");
    // Your program must then convert the given string into a series of 8-bit binary numbers, one for each character of the string.
    int length = strlen(msg);
    for (int ticker = 0; ticker < length; ticker++)
    {
        int ascii = msg[ticker];

        int binary[] = {0, 0, 0, 0, 0, 0, 0, 0};
        int ticker2 = 0;
        while (ascii > 0)
        {
            // Convert string to ascii integers
            // Test works: printf("ascii: %i\n", ascii);
            // Next convert ascii to binary. Decimal to binary converts by:
            // Step 1 dividing ascii by 2 and taking remainder and using this remainder in binary string.
            binary[ticker2] = ascii % 2;
            // Step 2 take quotient of ascii/2 and get %2 of it, store this in binary string
            // Step 3 keep dividing quotient by two and storing the remainder in the binary string until you have 0
            // Step 4 String stores zeros until the byte is filled
            ascii = ascii / 2;
            ticker2++;
        }
        for (int ticker3 = BITS_IN_BYTE - 1; ticker3 >= 0; ticker3--)
        {
            print_bulb(binary[ticker3]);
        }
        printf("\n");
    }
}

void print_bulb(int bit)
{
    if (bit == 0)
    {
        // Dark emoji
        printf("\U000026AB");
    }
    else if (bit == 1)
    {
        // Light emoji
        printf("\U0001F7E1");
    }
}
// the function is not working when it gets to the binary conversion section.