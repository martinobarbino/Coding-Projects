#include "helpers.h"

void colorize(int height, int width, RGBTRIPLE image[height][width])
{
    // Change all black pixels to a color of your choosing
    // Take the value for the pixels, and swap them with values for green
    // Use a for loop to go through all the parts of the image
    // If the for loop detects the black, swap black with green

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            if (image[i][j].rgbtRed == 0)
            {
                image[i][j].rgbtGreen = 255;
            }
        }
    }
}
