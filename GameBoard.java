import java.util.ArrayList;

public class GameBoard {
    private List<room> rooms;
    
    public GameBoard()
    {

    }
    

    public ArrayList<room> createRooms()
    {
        ArrayList<room> tempRooms = new ArrayList<room>();

        tempRooms.add(new room(null));

        for(int i = 0; i < 2; i++)
        {
            int[][] loc = new int[0][i];
            tempRooms.add();
        }
    }
}
