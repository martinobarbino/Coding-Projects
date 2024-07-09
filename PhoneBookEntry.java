public class PhoneBookEntry
{
    private String name;
    private String phoneNumber;
    
    public PhoneBookEntry(String n, String pn)
    {
        name = n;
        phoneNumber = pn;
    }
    
    public void setName(String n)
    {
        name = n;
    }
    
    public void setPhoneNumber(String pn)
    {
        phoneNumber = pn;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}