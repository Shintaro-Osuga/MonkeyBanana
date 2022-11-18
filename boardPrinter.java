import java.util.Arrays;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

public class boardPrinter {

    /* prints the board by gooing through each cell in the 2d room array */
    public void printBoard(room[][] board)
    {
        System.out.println("-----------------");
        for(int i = 0; i < 2; i++)
        {
            System.out.print("| ");
            String items = "";
            for(int j = 0; j < 2; j++)
            {
                items = itemsInRoom(board[i][j]);
                System.out.print(board[i][j].getRoomName() + " ");
                //This is just putting blanks spaces so it doesnt look bad and the lines, line up
                if(items.length() != 4)
                {
                    for(int k = 0; k < 4 - items.length(); k++)
                    {
                        System.out.print(" ");
                    }
                }
                System.out.print(items);

                System.out.print(" |");
            }
            System.out.println();
            System.out.print("-----------------");
            System.out.println();
        }
    }

    /* checks if a item is in the given room and adds cooresponding string values to the output string */
    public String itemsInRoom(room room)
    {
        String output = "";
        int size  = 0;
        try
        {
            size = room.getItemNames().size();
        }catch(NullPointerException e)
        {
            // System.out.println("There is nothing in the list");
            return output;
        }

        for(int i = 0; i < room.getItemNames().size(); i++)
        {
            String itemName = room.getItemNames().get(i);

            switch(itemName){
                case "banana":
                    output = output + "B";
                    break;
                case "box":
                    output = output + "[]";
                    break;
                case "monkey":
                    output = output + "M";
                    break;
                default:
                    output = output;
                    break;
            }
        }
        return output;
    }
    
}
