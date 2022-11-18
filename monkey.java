public class monkey implements object
{
    private int[] location;
    private boolean height;

    private boolean hasBanana;

    private boolean isOnBox;

    private String name = "monkey";

    public monkey()
    {
        height = false;
        hasBanana = false;
        isOnBox = false;
    }

    public void changeHeight()
    {
        height = !height;
    }

    public void changeLocation(int[] newLocation)
    {
        location = newLocation;
    }

    public void changeBananaStatus()
    {
        hasBanana =  !hasBanana;
    }

    public void changeBoxStatus()
    {
        isOnBox = !isOnBox;
    }

    public String getName()
    {
        return name;
    }

}