import java.util.Random;   // Needed for the Random class

/**			
   This class simulates rolling a pair of dice 10,000 times
   and counts the number of times doubles of are rolled for
   each different pair of doubles.
*/

public class DiceSimulation
{
	public static void main(String[] args)
	{
		final int NUMBER = 10000;  // Number of dice rolls

		// A random number generator used in        
		// simulating the rolling of dice
		Random generator = new Random();

		int die1Value;       // Value of the first die
        int die2Value;       // Value of the second die
		int count = 0;       // Total number of dice rolls
        int snakeEyes = 0;   // Number of snake eyes rolls
		int twos = 0;		 // Number of double two rolls
        int threes = 0;      // Number of double three rolls
		int fours = 0;       // Number of double four rolls
        int fives = 0;       // Number of double five rolls
		int sixes = 0;       // Number of double six rolls

		// TASK #1 Enter your code for the algorithm here
        // Repeat while the number of dice rolls are less than the
        // number of times the dice should be rolled.
        while (count < 10000)
        {
            // Get the value of the first die by rolling the first die
            die1Value = generator.nextInt(6) + 1;
            // Get the value of the second die by rolling the second die
            die2Value = generator.nextInt(6) +1;
            // If the value of the first die is the same as the value            
            // of the second die
            if (die1Value == die2Value)
            {
                // If value of first die is 1
                if (die1Value == 1)
                {
                    // Increment the number of times snake eyes were rolled
                    snakeEyes++;
                }
                // Else if value of the first die is 2
                else if (die1Value == 2)
                {
                    // Increment the number of times twos were rolled
                    twos++;
                }
                // Else if value of the first die is 3
                else if (die1Value == 3)
                {
                    // Increment the number of times threes were rolled
                    threes++;
                }
                // Else if value of the first die is 4
                else if (die1Value == 4)
                {
                    // Increment the number of times fours were rolled
                    fours++;
                }
                // Else if value of the first die is 5
                else if (die1Value == 5)
                {
                    // Increment the number of times fives were rolled
                    fives++;
                }
                // Else if value of the first die is 6
                else if (die1Value == 6)
                {    
                    // Increment the number of times sixes were rolled
                    sixes++;
                }
            }
            // Increment the number of times the dice were rolled
            count++;
        }
        // Print the label of the loop method
        System.out.println("@@@@@@@@@@ while loop @@@@@@@@@@");
		// Display the results
		System.out.println ("You rolled snake eyes " +
                            snakeEyes + " out of " +
                            count + " rolls.");
		System.out.println ("You rolled double twos " +
						    twos + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double threes " +
                            threes + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fours " +
                            fours + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fives " +
                            fives + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double sixes " +
                            sixes + " out of " + count +
                            " rolls.");
        // TASK #2 Perform the same task as in Task #1 but with a do while loop
        // Reset variables
        count = 0;
        snakeEyes = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        // Do while loop for the dice rolling simulation
        do
        {
            // Get the value of the first die by rolling the first die
            die1Value = generator.nextInt(6) + 1;
            // Get the value of the second die by rolling the second die
            die2Value = generator.nextInt(6) +1;
            // If the value of the first die is the same as the value            
            // of the second die
            if (die1Value == die2Value)
            {
                // If value of first die is 1
                if (die1Value == 1)
                {
                    // Increment the number of times snake eyes were rolled
                    snakeEyes++;
                }
                // Else if value of the first die is 2
                else if (die1Value == 2)
                {
                    // Increment the number of times twos were rolled
                    twos++;
                }
                // Else if value of the first die is 3
                else if (die1Value == 3)
                {
                    // Increment the number of times threes were rolled
                    threes++;
                }
                // Else if value of the first die is 4
                else if (die1Value == 4)
                {
                    // Increment the number of times fours were rolled
                    fours++;
                }
                // Else if value of the first die is 5
                else if (die1Value == 5)
                {
                    // Increment the number of times fives were rolled
                    fives++;
                }
                // Else if value of the first die is 6
                else if (die1Value == 6)
                {    
                    // Increment the number of times sixes were rolled
                    sixes++;
                }
            }
            // Increment the number of times the dice were rolled
            count++;
        } while (count < 10000);
        // Print the label of the loop method
        System.out.println("@@@@@@@@@@ do-while loop @@@@@@@@@@");
		// Display the results
		System.out.println ("You rolled snake eyes " +
                            snakeEyes + " out of " +
                            count + " rolls.");
		System.out.println ("You rolled double twos " +
						    twos + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double threes " +
                            threes + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fours " +
                            fours + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fives " +
                            fives + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double sixes " +
                            sixes + " out of " + count +
                            " rolls.");                   
        // TASK #2 Perform the same task as in Task #1 but with a for loop
        // Reset variables
        count = 0;
        snakeEyes = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        // For loop for the dice rolling simulation
        for (count = 0; count < 10000; count++)
        {
            // Get the value of the first die by rolling the first die
            die1Value = generator.nextInt(6) + 1;
            // Get the value of the second die by rolling the second die
            die2Value = generator.nextInt(6) +1;
            // If the value of the first die is the same as the value            
            // of the second die
            if (die1Value == die2Value)
            {
                // If value of first die is 1
                if (die1Value == 1)
                {
                    // Increment the number of times snake eyes were rolled
                    snakeEyes++;
                }
                // Else if value of the first die is 2
                else if (die1Value == 2)
                {
                    // Increment the number of times twos were rolled
                    twos++;
                }
                // Else if value of the first die is 3
                else if (die1Value == 3)
                {
                    // Increment the number of times threes were rolled
                    threes++;
                }
                // Else if value of the first die is 4
                else if (die1Value == 4)
                {
                    // Increment the number of times fours were rolled
                    fours++;
                }
                // Else if value of the first die is 5
                else if (die1Value == 5)
                {
                    // Increment the number of times fives were rolled
                    fives++;
                }
                // Else if value of the first die is 6
                else if (die1Value == 6)
                {    
                    // Increment the number of times sixes were rolled
                    sixes++;
                }
            }
        }
        // Print the label of the loop method
        System.out.println("@@@@@@@@@@ for loop @@@@@@@@@@");
		// Display the results
		System.out.println ("You rolled snake eyes " +
                            snakeEyes + " out of " +
                            count + " rolls.");
		System.out.println ("You rolled double twos " +
						    twos + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double threes " +
                            threes + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fours " +
                            fours + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double fives " +
                            fives + " out of " + count +
                            " rolls.");
		System.out.println ("You rolled double sixes " +
                            sixes + " out of " + count +
                            " rolls.");
        System.out.println("");
	}
}		