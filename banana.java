public class banana implements object{
    private int[][] location;
    private boolean height;


    public banana(boolean height, int[][] location)
    {
        this.location = location;
        this.height = height;
    }
    public void changeHeight(boolean newHeight)
    {
        height = newHeight;
    }

    public void changeLocation(int[][] newLocation)
    {
        location = newLocation;
    }

}
