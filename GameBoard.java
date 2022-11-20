import java.util.*;
import java.math.*;
import java.util.concurrent.*;

public class GameBoard {
    private room[][] rooms;
    private List<object> items;
    private List<String> itemNames;

    private String roomMonkeyIn;
    private String roomBoxIn;
    private String roomBananasIn;
    private String monkeyHeight;
    private boolean monkeyHasBananas;

    private int[] monkeyIndex;
    private int[] boxIndex;
    private int[] bananaIndex;

    private int[] roomAIndex;
    private int[] roomBIndex;
    private int[] roomCIndex;
    
    
    public GameBoard()
    {
        rooms = new room[2][2];
        itemNames = new ArrayList<String>();
        items = new ArrayList<object>();
        monkeyHasBananas = false;
        monkeyHeight = "LOW";
        createRooms();
        createItems();
    }


    public GameBoard copyBoard()
    {
        GameBoard copy = new GameBoard();

        copy.setItemNames(itemNames);
        copy.setMonkeyHeight(monkeyHeight);

        copy.setRoomBananaIn(roomBananasIn);
        copy.setRoomBoxIn(roomBoxIn);
        copy.setRoomMonkeyIn(roomMonkeyIn);

        copy.setRooms(rooms);
        copy.setbananaIndex(bananaIndex);
        copy.setboxIndex(boxIndex);

        copy.setroomAIndex(roomAIndex);
        copy.setroomBIndex(roomBIndex);
        copy.setroomCIndex(roomCIndex);

        copy.setmonkeyHasBananas(monkeyHasBananas);
        copy.setmonkeyIndex(monkeyIndex);
        copy.setItems(items);

        return copy;
    }
    
    /* Creates and initializes rooms in the 2d array of rooms */
    private void createRooms()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                int[] temp = new int[]{i,j};
                rooms[i][j] = new room(temp);
            }
        }
        //gives them names at random
        giveRoomNames();
        //assigns the adjacents to each room
        fillAdjacents();
    }


    /* uses threadlocalrandom to get a random int between 0 and 1, so either 0 or 1
     * uses them and gives that room a name if the room doesn't already have a name
     */
    private void giveRoomNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        names.add("A");
        names.add("B");
        names.add("C");
        while(0 != names.size())
        {
            int x = ThreadLocalRandom.current().nextInt(0, 1 + 1);;
            int y = ThreadLocalRandom.current().nextInt(0, 1 + 1);
            if(rooms[x][y].hasName() == false)
            {
                rooms[x][y].setRoomName(names.get(0));

                int[] temp = new int[]{x,y};

                //stores indices of rooms A, B, and C
                switch (names.get(0))
                {
                    case "A":
                        roomAIndex = temp;
                        break;
                    case "B":
                        roomBIndex = temp;
                        break;
                    case "C":
                        roomCIndex = temp;
                        break;
                }
                // System.out.println("room");
                // //sets values for room monkey, banana, box in
                // if(rooms[x][y].getItemNames().contains("monkey"))
                // {
                //     System.out.println("room monkey in");
                //     roomMonkeyIn = names.get(0);
                //     monkeyIndex = temp;
                // }else if(rooms[x][y].getItemNames().contains("banana"))
                // {
                //     roomBananasIn = names.get(0);
                //     bananaIndex = temp;
                // }else if(rooms[x][y].getItemNames().contains("box"))
                // {
                //     roomBoxIn = names.get(0);
                //     boxIndex = temp;
                // }

                names.remove(names.get(0));
            }
        }
    }

    /* Gives each room in the 2d array of rooms two adjacent rooms
    * in a way setting pointers between the rooms in the 2d array of rooms.
    * this is accomplished by getting the math.abs of the cells index minus one 
     */
    private void fillAdjacents()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                rooms[i][j].addAdjacentRoom(rooms[Math.abs(i-1)][j]);
                rooms[i][j].addAdjacentRoom(rooms[i][Math.abs(j-1)]);
            }
        }
    }

    /* instantiates each item monkey, banana, box */
    public void createItems()
    {
        items.add(new monkey());
        monkeyHeight = "LOW";
        itemNames.add("monkey");
        items.add(new banana(true));
        itemNames.add("banana");
        items.add(new box());
        itemNames.add("box");
    }

    

    /* I most likely will go back and redo this so its less BAD */
    /* TRIPLE for loop. the first two are used to get the index location for the 2d array of rooms
     * the third is used to iterate through every object
     * if the roomname and the objectname are the same then the object is placed in to the rooms item list
     */
    public void placeItem(String roomName, String objectName)
    {
        //goes through the list of names and if the roomName given is the same as name
        //runs switch add item
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                for(int k = 0; k < items.size(); k++)
                {
                    if(items.get(k).getName().equals(objectName) && rooms[i][j].getRoomName().equals(roomName))
                    {
                        rooms[i][j].addItem(items.get(k));
                        switch(objectName)
                        {
                            case "monkey":
                                roomMonkeyIn = rooms[i][j].getRoomName();
                                monkeyIndex = new int[]{i,j};
                                break;
                            case "banana":
                                roomBananasIn = rooms[i][j].getRoomName();
                                bananaIndex = new int[]{i,j};
                                break;
                            case "box":
                                System.out.println("Setting box location to");
                                roomBoxIn = rooms[i][j].getRoomName();
                                boxIndex = new int[]{i,j};
                                break;
                            }
                    }
                }
            }
        }
    }

    //Removes monkey from previous location, changes monkeyIndex to new index, and adds monkey to the new room
    public void moveMonkey(String to)
    {
        switch(to)
        {
            case "A":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(0));

                roomMonkeyIn = "A";
                monkeyIndex = roomAIndex;

                rooms[roomAIndex[0]][roomAIndex[1]].addItem(items.get(0));
                break;
            case "B":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(0));

                roomMonkeyIn = "B";
                monkeyIndex = roomBIndex;

                rooms[roomBIndex[0]][roomBIndex[1]].addItem(items.get(0));
                break;
            case "C":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(0));

                roomMonkeyIn = "C";
                monkeyIndex = roomCIndex;

                rooms[roomCIndex[0]][roomCIndex[1]].addItem(items.get(0));
                break;
            default:
                System.out.println("In switch");
                break;
        }
    }

    public void moveBox(String to)
    {
        switch(to)
        {
            case "A":
                rooms[boxIndex[0]][boxIndex[1]].removeItem(items.get(2));

                roomBoxIn = "A";
                boxIndex = roomAIndex;

                rooms[roomAIndex[0]][roomAIndex[1]].addItem(items.get(2));
                break;
            case "B":
                rooms[boxIndex[0]][boxIndex[1]].removeItem(items.get(2));

                roomBoxIn = "B";
                boxIndex = roomBIndex;
                
                rooms[roomBIndex[0]][roomBIndex[1]].addItem(items.get(2));
                break;
            case "C":
                rooms[boxIndex[0]][boxIndex[1]].removeItem(items.get(2));

                roomBoxIn = "C";
                boxIndex = roomCIndex;

                rooms[roomCIndex[0]][roomCIndex[1]].addItem(items.get(2));
                break;
        }
    }

    public boolean monkeyHasBananas()
    {
        return monkeyHasBananas;
    }

    public void updateMonkeyHeight(String height)
    {
        items.get(0).changeHeight();
        monkeyHeight = height;
    }

    public room[][] getRooms()
    {
        return rooms;
    }

    public List<String> getItemNameList()
    {
        return itemNames;
    }

    public List<object> getItems()
    {
        return items;
    }

    public String getRoomNameMonkeyIn()
    {
        return roomMonkeyIn;
    }
    
    public String getRoomNameBoxIn()
    {
        return roomBoxIn;
    }

    public String getRoomNameBananaIn()
    {
        return roomBananasIn;
    }

    public room getRoomMonkeyIn()
    {
        return rooms[monkeyIndex[0]][monkeyIndex[1]];
    }

    public boolean isMonkeyAt(String roomName)
    {
        return roomMonkeyIn.equals(roomName);
    }

    public boolean isBoxAt(String roomName)
    {
        return roomBoxIn.equals(roomName);
    }

    public boolean isBananasAt(String roomName)
    {
        return roomBananasIn.equals(roomName);
    }

    public String getMonkeyHeight()
    {
        return monkeyHeight;
    }

    public object getMonkey()
    {
        return items.get(0);
    }

    public object getBox()
    {
        return items.get(2);
    }

    public object getBanana()
    {
        return items.get(items.indexOf(1));
    }

    public void setRooms(room[][] room)
    {
        rooms = room;
    }

    public void setItemNames(List<String> itemNames)
    {
        this.itemNames = itemNames;
    }

    public void setRoomMonkeyIn(String roomMonkeyIn)
    {
        this.roomMonkeyIn = roomMonkeyIn;
    }

    public void setRoomBoxIn(String roomBoxIn)
    {
        this.roomBoxIn = roomBoxIn;
    }
    
    public void setRoomBananaIn(String roomBananaIn)
    {
        this.roomBananasIn = roomBananaIn;
    }
    
    public void setMonkeyHeight(String height)
    {
        this.monkeyHeight = height;
    }    
    
    public void setmonkeyHasBananas(boolean monkeyHasBananas){
        this.monkeyHasBananas = monkeyHasBananas;
    }
    
    public void setmonkeyIndex(int[] monkeyIndex){
        this.monkeyIndex = monkeyIndex;
    }

    public void setboxIndex(int[] boxIndex){
        this.boxIndex = boxIndex;
    }

    public void setbananaIndex(int[] bananaIndex){
        this.bananaIndex = bananaIndex;
    }
    
    public void setroomAIndex(int[] roomAIndex){
        this.roomAIndex = roomAIndex;
    }
    
    public void setroomBIndex(int[] roomBIndex){
        this.roomBIndex = roomBIndex;
    }

    public void setroomCIndex(int[] roomCIndex){
        this.roomCIndex = roomCIndex;
    }

    public void setItems(List<object> items)
    {
        this.items = items;
    }









    
    
    
    
    
    
    
    
    
    
    
    
    

    /* might implement this later so the user doesnt need to specify monkey, banana, box locations */
    private void RandomplaceItems()
    {
        int num_added = 0;

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                for(int k = 0; k < items.size(); k++)
                {
                    if((Math.random()*((16-0)+0)) % (4 - num_added) == 0 || num_added == items.size())
                    {
                        int[] temploc = new int[]{i,j};
                        items.get(k).changeLocation(temploc);
                        rooms[i][j].addItem(items.get(k));
                        num_added++;
                    }
                }
            }
        }
    }

}
