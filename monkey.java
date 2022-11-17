public class monkey implements object
{
    private int[][] location;
    private boolean height;

    private boolean hasBanana;

    public void changeHeight(boolean newHeight)
    {
        height = newHeight;
    }

    public void changeLocation(int[][] newLocation)
    {
        location = newLocation;
    }

    public void changeBananaStatus()
    {
        hasBanana =  !hasBanana;
    }
}