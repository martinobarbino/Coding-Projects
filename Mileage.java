import java.util.Scanner;       // Scanner used to get user input

/**
    This program calculates gas mileage based on user input of miles driven
    and gas used, then outputs the result.
*/
public class Mileage
{		
	public static void main (String [] args)
	{
        // Declare variables
        double milesDriven;     // User inputted value for miles driven
        double gallonsUsed;     // User inputted value for gallons used
        double milesPerGallon;  // Calculated value for miles per gallon
        
        // Create a Scanner object to read user input
        Scanner keyboard = new Scanner(System.in);
        
        // Tell the user what the program will do
        System.out.println("This program will calculate gas mileage.");
        
        // Prompt the user to input the number of miles they've driven
        System.out.print("Please enter the number of miles you have driven: ");
        
        // Read the user's input with Scanner into variable for miles
        milesDriven = keyboard.nextDouble();
        
        // Prompt the user to input the number of gallons they've used
        System.out.print("Please enter the number" 
                          + " of gallons you have used: ");
        
        // Read the user's input with Scanner into the variable for gallons
        gallonsUsed = keyboard.nextDouble();
        
        // Calculate the miles per gallon using formula and user input
        milesPerGallon = milesDriven / gallonsUsed;
        
        // Output the result with label of result
        System.out.println("Your gas mileage is " + milesPerGallon
                            + " miles per gallon.\n");
    }
}
/* 
    The purpose of this calculation is to approximate how many miles can
    be driven for each gallon of gas in the tank
*/