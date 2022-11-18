public class box implements object{
    private int[] location;
    private boolean height;

    private String name = "box";

    public box()
    {
        height = false;
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
