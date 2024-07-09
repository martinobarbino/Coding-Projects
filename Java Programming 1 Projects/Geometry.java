import java.util.Scanner;

/**
    This program makes use of  static methods
*/

public class Geometry
{
    public static void main(String[] args)
    {
        int choice;       // The user's choice
        double value = 0; // The method's return value
		char letter;      // The user's Y or N decision
		double radius;    // The radius of the circle
		double length;    // The length of the rectangle
		double width;     // The width of the rectangle
		double height;    // The height of the triangle
		double base;      // The base of the triangle
		double side1;     // The first side of the triangle
		double side2;     // The second side of the triangle
		double side3;     // The third side of the triangle

		// Create a scanner object to read from the keyboard
		Scanner keyboard = new Scanner(System.in);

		// The do loop allows the menu to be displayed first
		do
		{
			// TASK #1 Call the printMenu method
            printMenu();

			choice = keyboard.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.print("Enter the radius of " +
                                       "the circle: ");
                    radius = keyboard.nextDouble();

                    // TASK #3 Call the circleArea method and
                    // store the result in the value variable
                    value = circleArea(radius);

                    System.out.println("The area of the " +
                                         "circle is " + value);
                    break;
                case 2:
                    System.out.print("Enter the length of " +
                                       "the rectangle: ");
                    length = keyboard.nextDouble();
                    System.out.print("Enter the width of " +
                                       "the rectangle: ");
                    width = keyboard.nextDouble();

                    // TASK #3 Call the rectangleArea method and
                    // store the result in the value variable
                    value = rectangleArea(length, width);

                    System.out.println("The area of the " +
                                         "rectangle is " + value);
                    break;
                case 3:
                    System.out.print("Enter the height of " +
                                       "the triangle: ");
                    height = keyboard.nextDouble();
                    System.out.print("Enter the base of " +
                                       "the triangle: ");
                    base = keyboard.nextDouble();

                    // TASK #3 Call the triangleArea method and
                    // store the result in the value variable
                    value = triangleArea(height, base);

                    System.out.println("The area of the " +
                                         "triangle is " + value);
                    break;
                case 4:
                    System.out.print("Enter the radius of " +
                                       "the circle: ");
                    radius = keyboard.nextDouble();

                    // TASK #3 Call the circumference method and
                    // store the result in the value variable
                    value = circleCircumference(radius);

                    System.out.println("The circumference " +
                                         "of the circle is " +
                                          value);
                    break;
                case 5:
                    System.out.print("Enter the length of " +
                                       "the rectangle: ");
                    length = keyboard.nextDouble();
                    System.out.print("Enter the width of " +
                                       "the rectangle: ");
                    width = keyboard.nextDouble();

                    // TASK #3 Call the perimeter method and
                    // store the result in the value variable
                    value = rectanglePerimeter(length, width);

                    System.out.println("The perimeter of " +
                                         "the rectangle is " +
                                          value);
                    break;
                case 6:
                    System.out.print("Enter the length of " +
                                       "side 1 of the " +
                                       "triangle: ");
                    side1 = keyboard.nextDouble();
                    System.out.print("Enter the length of " +
                                       "side 2 of the " +
                                       "triangle: ");
                    side2 = keyboard.nextDouble();
                    System.out.print("Enter the length of " +
                                       "side 3 of the " +
                                       "triangle: ");
                    side3 = keyboard.nextDouble();

                    // TASK #3 Call the perimeter method and
                    // store the result in the value variable
                    value = trianglePerimeter(side1, side2, side3);

                    System.out.println("The perimeter of " +
                                         "the triangle is " +
                                          value);
                    break;
                default:
                    System.out.println("You did not enter " +
                                         "a valid choice.");
            }
            keyboard.nextLine(); // Consume the new line

            System.out.println("Do you want to exit " +
                                 "the program (Y/N)?: ");
            String answer = keyboard.nextLine();
            letter = answer.charAt(0);

        } while(letter != 'Y' && letter != 'y');
    }

    // TASK #1 Create the printMenu method here
    /**
        This method prints a user-interface menu.
        This method requires no parameters to operate and returns nothing, 
        it simply prints out a menu for the user.
    */
    public static void printMenu()
    {
        // Print the menu according to specifications
        System.out.println("This is a geometry calculator");
        System.out.println("Choose what you would like to calculate");
        System.out.println("1. Find the area of a circle");
        System.out.println("2. Find the area of a rectangle");
        System.out.println("3. Find the area of a triangle");
        System.out.println("4. Find the circumference of a circle");
        System.out.println("5. Find the perimeter of a rectangle");
        System.out.println("6. Find the perimeter of a triangle");
        System.out.print("Enter the number of your choice: ");
    }
    
    // TASK #2 Create the value-returning methods here
    // TASK #4 Write javadoc comments for each method
    /**
        This method calculates the area of a circle.
        This program requires a value for the radius parameter to operate
        and calculates a value for the area of a circle with the radius
        described in the parameter.
        @param radius The user-inputted value for the radius of the circle.
        @return The calculated value of the area of the circle. 
    */
    public static double circleArea(double radius)
    {
        // Declare variables
        double area;    // Variable for storing area
        
        // Perform area calculation and return area
        area = Math.PI * (radius * radius);
        
        return area;
    }
    
    /**
        This method calculates the area of a rectangle.
        This program requires values for the length and width parameters 
        to operate and calculates a value for the area of a rectangle with the 
        dimensions described in the parameter list.
        @param length The user-inputted value for the length of the rectangle.
        @param width The user-inputted value for the length of the rectangle.
        @return The calculated value of the area of the rectangle. 
    */
    public static double rectangleArea(double length, double width)
    {
        // Declare variables
        double area;    // Variable for storing area
        
        // Perform area calculation and return
        area = length * width;
        return area;
    }
    
    /**
        This method calculates the area of a triangle.
        This program requires values for the height and base parameters 
        to operate and calculates a value for the area of a triangle with the 
        dimensions described in the parameter list.
        @param height The user-inputted value for the height of the triangle.
        @param base The user-inputted value for the base of the triangle.
        @return The calculated value of the area of the triangle. 
    */
    public static double triangleArea(double height, double base)
    {
        // Declare variables
        double area;    // Variable for storing area
        
        // Perform area calculation and return
        area = 0.5 * base * height;
        return area;
    }
    
    /**
        This method calculates the circumference of a circle.
        This program requires a value for the radius parameter to operate
        and calculates a value for the circumference of a circle with the 
        radius described in the parameter.
        @param radius The user-inputted value for the radius of the circle.
        @return The calculated value of the circumference 
                of the circle. 
    */
    public static double circleCircumference(double radius)
    {
        // Declare variables
        double circumference;   // Variable for storing circumference
        
        // Perform circumference calculation and return
        circumference = 2 * Math.PI * radius;
        return circumference;
    }
    
    /**
        This method calculates the perimeter of a rectangle.
        This program requires values for the length and width parameters 
        to operate and calculates a value for the perimeter of a rectangle 
        with the dimensions described in the parameter list.
        @param length The user-inputted value for the length of the rectangle.
        @param width The user-inputted value for the length of the rectangle.
        @return The calculated value of the perimeter of 
                the rectangle. 
    */
    public static double rectanglePerimeter(double length, double width)
    {
        // Declare variables
        double perimeter;       // Variable for storing perimeter
        
        // Perform perimeter calculation and return
        perimeter = (2 * length) + (2 * width);
        return perimeter;
    }
    
    /**
        This method calculates the perimeter of a triangle.
        This program requires values for all three sides of a triangle
        to operate and calculates a value for the perimeter of a triangle 
        with the sides specified above.
        @param s1 The user-inputted value for one side of the triangle.
        @param s2 The user-inputted value for one side of the triangle.
        @param s3 The user-inputted value for one side of the triangle.
        @return The calculated value of the perimeter of 
                the triangle. 
    */
    public static double trianglePerimeter(double s1, double s2, double s3)
    {
        // Declare variables
        double perimeter;       // Variable for storing perimeter
        
        // Perform perimeter calculation and return
        perimeter = (s1 + s2 + s3);
        return perimeter;
    }
}