#include "helpers.h"
#include <cs50.h>
#include <math.h>
#include <stdio.h>

// Convert image to grayscale
void grayscale(int height, int width, RGBTRIPLE image[height][width])
{
    // Two for loops for h and w. Analyses each pixel.
    // In the inner loop, if the RGB values aren't equal to eachother:
    // Take average of all three RGB values
    // Set all three sections of RGB to this average

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            if (image[i][j].rgbtBlue != image[i][j].rgbtGreen || image[i][j].rgbtBlue != image[i][j].rgbtRed ||
                image[i][j].rgbtRed != image[i][j].rgbtGreen)
            {
                float average = (image[i][j].rgbtBlue + image[i][j].rgbtRed + image[i][j].rgbtGreen) / 3.0;
                int grayscale = round(average);
                image[i][j].rgbtBlue = grayscale;
                image[i][j].rgbtRed = grayscale;
                image[i][j].rgbtGreen = grayscale;
            }
        }
    }
    return;
}

// Convert image to sepia
void sepia(int height, int width, RGBTRIPLE image[height][width])
{
    // Two for loops like in the grayscale function
    // In the innermost for loop, apply the algorithm on the rgbt values
    // Next, two if statements
    // #1 if the sepia value is less than 255, replace the rgbt value with the sepia value
    // Else, the sepia value just becomes 255
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            int sepiaRed = round((.393 * image[i][j].rgbtRed) + (.769 * image[i][j].rgbtGreen) + (.189 * image[i][j].rgbtBlue));
            if (sepiaRed > 255)
            {
                sepiaRed = 255;
            }
            int sepiaGreen = round((.349 * image[i][j].rgbtRed) + (.686 * image[i][j].rgbtGreen) + (.168 * image[i][j].rgbtBlue));
            if (sepiaGreen > 255)
            {
                sepiaGreen = 255;
            }
            int sepiaBlue = round((.272 * image[i][j].rgbtRed) + (.534 * image[i][j].rgbtGreen) + (.131 * image[i][j].rgbtBlue));
            if (sepiaBlue > 255)
            {
                sepiaBlue = 255;
            }
            image[i][j].rgbtBlue = sepiaBlue;
            image[i][j].rgbtRed = sepiaRed;
            image[i][j].rgbtGreen = sepiaGreen;
        }
    }
    return;
}

// Reflect image horizontally
void reflect(int height, int width, RGBTRIPLE image[height][width])
{
    // Again, need two for loops like above.
    // Make variable equal to  width - 1 - j. This is placeholder for opposite side of row
    // Inner for loop stops half way through row, because by then the pixels are swapped
    // Swaps pixels using method shown in lecture
    int len = width / 2;
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < len; j++)
        {
            int m = width - 1 - j;
            RGBTRIPLE tmp = image[i][j];
            image[i][j] = image[i][m];
            image[i][m] = tmp;
        }
    }
    return;
}

// Blur image
void blur(int height, int width, RGBTRIPLE image[height][width])
{
    // Take image[i][j], find its surrounding pxls.
    // Take sum of all of these and divide by 9
    // Set image[i][j] = to result
    // Create a copy of the image
    RGBTRIPLE copy[height][width];
    for (int g = 0; g < height; g++)
    {
        for (int h = 0; h < width; h++)
        {
            copy[g][h].rgbtBlue = image[g][h].rgbtBlue;
            copy[g][h].rgbtGreen = image[g][h].rgbtGreen;
            copy[g][h].rgbtRed = image[g][h].rgbtRed;
        }
    }
    // Scan the image
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            float pxlsumBlue = 0;
            float pxlsumGreen = 0;
            float pxlsumRed = 0;
            int count = 0;
            // Scan grid around image[i][j]
            for (int x = -1; x < 2; x++)
            {
                for (int y = -1; y < 2; y++)
                {
                    // If grid around image[i][j] is in bounds:
                    if (i + x >= 0 && i + x < height && j + y >= 0 && j + y < width)
                    {
                        // Loop needs to sum each new iteration pixel it encounters
                        pxlsumBlue += copy[i + x][j + y].rgbtBlue;
                        pxlsumGreen += copy[i + x][j + y].rgbtGreen;
                        pxlsumRed += copy[i + x][j + y].rgbtRed;
                        // Need to use count in case sum is from fewer edge pixels
                        count++;
                    }
                }
            }
            int blrpxlBlue = round(pxlsumBlue / count);
            int blrpxlGreen = round(pxlsumGreen / count);
            int blrpxlRed = round(pxlsumRed / count);
            image[i][j].rgbtBlue = blrpxlBlue;
            image[i][j].rgbtGreen = blrpxlGreen;
            image[i][j].rgbtRed = blrpxlRed;
        }
    }
    return;
}
