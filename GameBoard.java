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
        createRooms();
        createItems();
    }

    public GameBoard copyBoard()
    {
        return this;
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

                //sets values for room monkey, banana, box in
                if(rooms[x][y].getItems().contains(monkey.class))
                {
                    roomMonkeyIn = names.get(0);
                    monkeyIndex = temp;
                }else if(rooms[x][y].getItems().contains(banana.class))
                {
                    roomBananasIn = names.get(0);
                    bananaIndex = temp;
                }else if(rooms[x][y].getItems().contains(box.class))
                {
                    roomBoxIn = names.get(0);
                    boxIndex = temp;
                }

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
        switch(objectName)
        {
            case "monkey":
                rooms[monkeyIndex[0]][monkeyIndex[1]].addItem(items.get(items.indexOf(monkey.class)));
                break;
            case "banana":
                rooms[bananaIndex[0]][bananaIndex[1]].addItem(items.get(items.indexOf(banana.class)));
                break;
            case "box":
                rooms[boxIndex[0]][boxIndex[1]].addItem(items.get(items.indexOf(box.class)));
                break;
        }
    }
    
    public void moveMonkey(String to)
    {
        switch(to)
        {
            case "A":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(items.indexOf(monkey.class)));
                monkeyIndex = roomAIndex;

                rooms[roomAIndex[0]][roomAIndex[1]].addItem(items.get(items.indexOf(monkey.class)));
            case "B":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(items.indexOf(monkey.class)));
                monkeyIndex = roomBIndex;
                
                rooms[roomBIndex[0]][roomBIndex[1]].addItem(items.get(items.indexOf(monkey.class)));
            case "C":
                rooms[monkeyIndex[0]][monkeyIndex[1]].removeItem(items.get(items.indexOf(monkey.class)));
                monkeyIndex = roomCIndex;

                rooms[roomCIndex[0]][roomCIndex[1]].addItem(items.get(items.indexOf(monkey.class)));
        }
    }

    public void updateMonkeyHeight(String height)
    {
        items.get(items.indexOf(monkey.class)).changeHeight();
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
        return 
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
        return items.get(items.indexOf(monkey.class));
    }

    public object getBox()
    {
        return items.get(items.indexOf(box.class));
    }

    public object getBanana()
    {
        return items.get(items.indexOf(banana.class));
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
