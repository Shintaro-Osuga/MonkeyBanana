public class banana implements object{
    private int[] location;
    private boolean height;

    private String name = "banana";

    public banana(boolean height)
    {
        this.height = height;
    }
    public void changeHeight()
    {
        height = !height;
    }

    public void changeLocation(int[] newLocation)
    {
        location = newLocation;
    }

    public String getName()
    {
        return name;
    }
}
