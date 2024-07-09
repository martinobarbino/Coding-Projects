#include <cs50.h>
#include <math.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        printf("Usage ./recover image\n");
        return 1;
    }
    // Open file
    FILE *card = fopen(argv[1], "r");
    if (card == NULL)
    {
        printf("Could not open file.\n");
        return 1;
    }
    // Declare variables
    int BLOCK_SIZE = 512;
    typedef uint8_t BYTE;
    int jcnt = -1;
    char filename[8];
    FILE *img = NULL;
    // Declare buffer array
    BYTE buffer[512];
    int card_total = fread(buffer, 1, BLOCK_SIZE, card);
    // Repeat until end of card
    while (fread(buffer, 1, BLOCK_SIZE, card) == BLOCK_SIZE)
    {
        // Detects JPEG header
        if (buffer[0] == 0xff && buffer[1] == 0xd8 && buffer[2] == 0xff && ((buffer[3] & 0xf0) == 0xe0))
        {
            // Counts number of jpegs found.
            jcnt++;
            if (jcnt == 0)
            {
                // First JPEG file made and starts to write
                sprintf(filename, "%03i.jpg", jcnt);
                img = fopen(filename, "w");
                // Now that file is open, write onto it
                fwrite(buffer, 1, card_total, img);
            }
            else
            {
                // Find next JPEG header and close previous then start new jpg and start writing
                fclose(img);
                sprintf(filename, "%03i.jpg", jcnt);
                img = fopen(filename, "w");
                fwrite(buffer, 1, card_total, img);
            }
        }
        else
        {
            // If the first JPEG is found, this continues writing after a block has ended
            if (jcnt != -1)
            {
                // Continues writing into file if a JPEG has been found
                fwrite(buffer, 1, card_total, img);
            }
        }
    }
    // After while loop, need to close remaining files
    fclose(img);
    fclose(card);
    return 0;
}