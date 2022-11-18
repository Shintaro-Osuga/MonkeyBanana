import java.util.*;

public class UI {
    private Scanner scan = new Scanner(System.in);

    /*asks for the input from the user for each item and as long as they dont give you 
     * the correct input
     */
    public String promptRoomforObject(String objectName)
    {
        System.out.println("Select which room the " + objectName + " starts in: ");
        System.out.println("[1] Room A");
        System.out.println("[2] Room B");
        System.out.println("[3] Room C");
        
        return scan.nextLine();
    }

    /* this is kinda primitive, i think it works but i dont know for sure
     * should make it so that, so long as the value given from the user is not
     * the value we want, which are stored in the possibleinputs list, it will return false
     * if it is in the list of possibleinputs it will return true.
     */
    public boolean verifyInput(String input, List<String> possibleInputs)
    {
        return possibleInputs.contains(input);
    }
}
