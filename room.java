import java.util.*;

public class room {
    private int[] location;
    private List<object> inRoom;
    private List<String> itemNames;
    private List<room> adjacentRooms;
    private String name;

    public room(int[] location)
    {
        name = "";
        this.location = location;
        inRoom = new ArrayList<object>();
        itemNames = new ArrayList<String>();
        adjacentRooms = new ArrayList<room>();
    }

    /* This'll just remove a object like monkey, banana, box from a room
     * has alot of error catching just in case
     */
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

    /* Will add item to list of objects in the room 
     * and also add the name to the itemNames list
    */
    public void addItem(object item)
    {
        inRoom.add(item);
        itemNames.add(item.getName());
    }

    /* This adds rooms that are adjacent to the list adjacent
     * called in the GameBoard where the adjacent rooms are found
     */
    public void addAdjacentRoom(room adjacent)
    {
        adjacentRooms.add(adjacent);
    }

    public List<object> getItems()
    {
        return inRoom;
    }

    public List<String> getItemNames()
    {
        return itemNames;
    }

    public void setRoomName(String roomName)
    {
        name = roomName;
    }
    
    public String getRoomName()
    {
        return name;
    }

    public boolean hasName()
    {
        if(name.equals(""))
        {
            return false;
        }else{
            return true;
        }
    }

    public boolean isAdjacentTo(room adjacentRoom)
    {
        return adjacentRooms.contains(adjacentRoom);
    }
}
