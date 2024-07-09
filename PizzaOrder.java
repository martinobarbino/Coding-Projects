import java.util.Scanner;  // Needed for the Scanner class		

/**			     
   This program allows the user to order a pizza.		
*/

public class PizzaOrder
{
	public static void main (String[] args)		
	{
		// Create a Scanner object to read input.
		Scanner keyboard = new Scanner (System.in);

		String firstName;             // User's first name
		boolean discount = false;     // Flag for discount	
		int inches;                   // Size of the pizza
		char crustType;			      // For type of crust
		String crust = "Hand-tossed"; // Name of crust		
		double cost = 12.99;          // Cost of the pizza
		final double TAX_RATE = .08;  // Sales tax rate
		double tax;                   // Amount of tax	     
		char choice;                  // User's choice
		String input;                 // User input          
		String toppings = "Cheese ";  // List of toppings
		int numberOfToppings = 0;     // Number of toppings

		// Prompt user and get first name.
		System.out.println("Welcome to Mike and " +
                           "Diane's Pizza");
		System.out.print("Enter your first name: ");
		firstName = keyboard.nextLine();

		// Determine if user is eligible for discount by
		// having the same first name as one of the owners.
		// ADD LINES HERE FOR TASK #1
        if (firstName.equalsIgnoreCase("Mike") == true 
            || firstName.equalsIgnoreCase("Diane") == true)
        {
            // Apply the discount if the customer has the same first name
            // as owners.
            discount = true;
        }

		// Prompt user and get pizza size choice.
		System.out.println("Pizza Size (inches)   Cost");
		System.out.println("        10            $10.99");	     
		System.out.println("        12			  $12.99");
		System.out.println("        14            $14.99");		
		System.out.println("        16            $16.99");
		System.out.println("What size pizza " +
                           "would you like?");
		System.out.print("10, 12, 14, or 16 " +
                         "(enter the number only): ");
		inches = keyboard.nextInt();

		// Set price and size of pizza ordered.
		// ADD LINES HERE FOR TASK #2
        // If-else-if statements for pizza size:
        if (inches == 10)
        {
            // Set price to 10.99
            cost = 10.99;
        }
        
        else if (inches == 12)
        {
            // Set price to 12.99
            cost = 12.99;
        }
        
        else if (inches == 14)
        {
            // Set price to 14.99
            cost = 14.99;
        }
        
        else if (inches == 16)
        {
            // Set price to 16.99
            cost = 16.99;
        }
        
        else
        {
            // Print a statement that the user's input was invalid
            // and set the pizza's size and cost to that of a 12 inch pizza
            System.out.println("That was not one of the choices, "
                                + "you will get a 12 inch pizza.");
            inches = 12;
            cost = 12.99;
        }
        
		// Consume the remaining newline character.
		keyboard.nextLine();

		// Prompt user and get crust choice.
		System.out.println("What type of crust " +
                           "do you want? ");     
		System.out.print("(H)Hand-tossed, " +
                         "(T) Thin-crust, or " +     
                         "(D) Deep-dish " +
                         "(enter H, T, or D): ");
		input = keyboard.nextLine();
		crustType = input.charAt(0);

		// Set user's crust choice on pizza ordered.
		// ADD LINES FOR TASK #3
        // Switch statement for setting the user's crust choice:
        switch (crustType)
        {
            // Cases for hand-tossed crust
            case 'h':
            case 'H':
                // Crust is hand-tosssed by default
                break;
                
            // Cases for thin crust
            case 't':
            case 'T':
                // Set the crust type to thin crust and break
                crust = "Thin-crust";
                break;
                
            // Cases for deep-dish crust
            case 'd':
            case 'D':
                // Set the crust type to deep-dish and break
                crust = "Deep-dish";
                break;
                
            // Case for if user has invalid input
            default:
                // Alert user that their input was invalid and as a result
                // they will get a hand-tossed pizza. 
                System.out.println("That was not one of the choices, "
                                    + "you will get Hand-tossed.");
        }

		// Prompt user and get topping choices one at a time.
		System.out.println("All pizzas come with cheese.");
		System.out.println("Additional toppings are " +
                           "$1.25 each, choose from:");		
		System.out.println("Pepperoni, Sausage, " +
                           "Onion, Mushroom");

		// If topping is desired,
		// add to topping list and number of toppings
		System.out.print("Do you want Pepperoni? (Y/N): ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + "Pepperoni ";
		}
		System.out.print("Do you want Sausage? (Y/N): ");
		input = keyboard.nextLine();		
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + "Sausage ";
		}
		System.out.print("Do you want Onion? (Y/N): ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + "Onion ";
		}
		System.out.print("Do you want Mushroom? (Y/N): ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + "Mushroom ";
		}

		// Add additional toppings cost to cost of pizza.
		cost = cost + (1.25 * numberOfToppings);

		// Display order confirmation.
		System.out.println();
		System.out.println("Your order is as follows: ");
		System.out.println(inches + " inch pizza");          
		System.out.println(crust + " crust");
		System.out.println(toppings);

		// Apply discount if user is eligible.
		// ADD LINES FOR TASK #4 HERE
        // Checks discount boolean variable and triggers if true
        if (discount)
        {
            // Print a message that the user is eligible for discount,
            // and apply the discount to the cost.
            System.out.println("You are eligible for a $2.00 discount");
            cost -= 2.0;
        }

		// EDIT PROGRAM FOR TASK #5
		// SO ALL MONEY OUTPUT APPEARS WITH 2 DECIMAL PLACES
		System.out.printf("The cost of your order " +
                          "is: $%.2f\n", cost);

		// Calculate and display tax and total cost.
		tax = cost * TAX_RATE;
		System.out.printf("The tax is: $%.2f\n", tax);
		System.out.printf("The total due is: $%.2f\n",
                          (tax + cost));

		System.out.println("Your order will be ready " +
                           "for pickup in 30 minutes.");
	}     
}		
