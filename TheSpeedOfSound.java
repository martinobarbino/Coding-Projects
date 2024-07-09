import java.util.Scanner;

public class TheSpeedOfSound
{
	public static void main(String[] args)
	{
        String medium;
        double distance;
        double time;
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Enter one of the following:"
                       + " air, water, or steel: ");
        medium = keyboard.nextLine();
        
        System.out.print("Enter the distance, in feet,"
                       + " the sound wave will travel: ");
        distance = keyboard.nextDouble();
        
        if (medium.equals("air"))
        {
            time = distance / 1100;
            System.out.print("It will take " + time + " seconds.");
        }
        
        else if (medium.equals("water"))
        {
            time = distance / 4900;
            System.out.print("It will take " + time + " seconds.");
        }
        
        else if (medium.equals("steel"))
        {
            time = distance / 16400;
            System.out.print("It will take " + time + " seconds.");
        }
        
        else
        {
            System.out.println("Unknown input");
        }
        System.out.println();
	}
}