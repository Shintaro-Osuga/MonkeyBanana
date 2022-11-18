import java.util.*;

public class Main{
    public static void main(String[] args) {
        GameBoard gameboard = new GameBoard();
        boardPrinter printer = new boardPrinter();
        UI ui = new UI();
     
        printer.printBoard(gameboard.getRooms());
    
        List<String> roomNames = Arrays.asList("A", "B", "C");

        for(int i = 0; i < gameboard.getItemNameList().size(); i++)
        {
            // System.out.print(ui.verifyInput(null, roomNames));
            String input = null;
            while(ui.verifyInput(input, roomNames) == false)
            {
                input = ui.promptRoomforObject(gameboard.getItemNameList().get(i));
            }
            System.out.println("Item: " + gameboard.getItemNameList().get(i) + " Room: " + input);
            gameboard.placeItem(input, gameboard.getItemNameList().get(i));
        }
        printer.printBoard(gameboard.getRooms());
        // printRoom(gameboard.getRooms());

    }
    
    /* these are helper methods for when i had the monkey in every room bug
     * i kept them for now but they will sadly be getting game ended sooner or later
     */
    public static void printRoom(room[][] room)
    {
        for(int i =0; i<2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                System.out.print(room[i][j].getRoomName());
                printItemNames(room[i][j].getItemNames());
            }
        }
    }
    public static void printItemNames(List<String> itemNames)
    {
        for(int i = 0; i < itemNames.size(); i++)
        {
            System.out.println(itemNames.get(i));
        }

    }
}