import java.util.*;

public class room {
    private int[][] location;
    private List<object> inRoom;

    public room(int[][] location)
    {
        this.location = location;
        inRoom = new ArrayList<object>();
    }

    public void removeItem(object item)
    {
        try
        {
            inRoom.remove(item);
        }
        catch (EmptyStackException e)
        {
            System.out.println("There are no items in this room");
        }
        catch (IndexOutOfBoundsException i)
        {
            System.out.println("The item is not in this room");
        }
    }

    public void addItem(object item)
    {
        inRoom.add(item);
    }
}
