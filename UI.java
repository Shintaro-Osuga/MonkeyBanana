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

    public String promptMoveToMake()
    {
        System.out.println("Select which move to make");
        System.out.println("Move(from, to)");
        System.out.println("Push(from, to)");
        System.out.println("ClimbUp(at)");
        System.out.println("ClimbDown(at)");
        System.out.println("Grab(at)");

        return scan.nextLine();
    }

    public String[] getTwoParams()
    {
        String inputs[] = new String[2];
        System.out.println("Start where");
        inputs[0] = scan.nextLine();
        System.out.println("Go where");
        inputs[1] = scan.nextLine();
        return inputs;
    }

    public String getOneParam()
    {
        System.out.println("Where is the monkey?");
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
