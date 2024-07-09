
public class NameAndInitials
{
	public static void main(String[] args)
	{
		// Declare + initialize name string variables
		String firstName = "Martin";
		String middleName = "Thomas";
		String lastName = "Miller";
		
		// Declare + initialize initial char variables
		char firstInitial = firstName.charAt(0);
		char middleInitial = middleName.charAt(0);
		char lastInitial = lastName.charAt(0);
		
		// Display variables
		System.out.println("Name: " + firstName + " " + middleName + " " 
							+ lastName);
		System.out.println("Initials: " + firstInitial + middleInitial 
							+ lastInitial);
	}
}