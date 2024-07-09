import javax.swing.JOptionPane;

public class TimeCalculator
{
	public static void main(String[] args)
	{
        double seconds;
        double minutes;
        double hours;
        double days;
   
        seconds = 
            Double.parseDouble(
            JOptionPane.showInputDialog("Enter the number of seconds."));
        
        if (seconds < 60)
        {
            JOptionPane.showMessageDialog(null, "That's less than a minute, "
                                          + "hour, or day.");
        }
        
        else
        {
            if (seconds >= 60)
            {
                minutes = seconds / 60;
                JOptionPane.showMessageDialog(null, "There are " + minutes
                                              + " minutes in " + seconds
                                              + " seconds.");
            }
            
            if (seconds >= 3600)
            {
                hours = seconds / 3600;
                JOptionPane.showMessageDialog(null, "There are " + hours
                                              + " hours in " + seconds
                                              + " seconds.");
            }
            
            if (seconds >= 86400)
            {
                days = seconds / 86400;
                JOptionPane.showMessageDialog(null, "There are " + days
                                              + " days in " + seconds
                                              + " seconds.");
            }
        }
        
        System.exit(0);
    }
}
