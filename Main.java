import java.util.*;

public class Main{
    public static void main(String[] args) {
        GameBoard gameboard = new GameBoard();
        boardPrinter printer = new boardPrinter();
        UI ui = new UI();
        MeanEndAnalysis MEA = new MeanEndAnalysis(gameboard);
        
        printer.printBoard(gameboard.getRooms());

        setItemLocations(gameboard, printer, ui);
        
        gameboard = MEA.MeanEndAnalysis();
        printer.printBoard(gameboard.getRooms());
        MEA.printActions();

        //for user playing the game
        // while(gameboard.monkeyHasBananas()==false)
        // {
        //     gameboard = makeMove(gameboard, printer, ui);
        //     printer.printBoard(gameboard.getRooms());
        // }
        // printRoom(gameboard.getRooms());
    }

    public static void output()
    {
        ArrayList<Class<? extends Action>> actions = new ArrayList<Class<? extends Action>>();

        actions.add(Grab.class);
        System.out.println(Arrays.deepToString(actions.get(0).getConstructors()));
    }

    public static GameBoard makeMove(GameBoard gameBoard, boardPrinter printer, UI ui)
    {
        List<String> moveList = Arrays.asList("Move", "Push", "ClimbUp", "ClimbDown", "Grab");
        String input = null;
        while(ui.verifyInput(input, moveList) == false)
        {
            input = ui.promptMoveToMake();
        }
        if(input.equals("Move"))
        {
            String[] params = ui.getTwoParams();
            Move move = new Move(params[0], params[1]);
            if(move.checkPreconditions(gameBoard))
            {
                gameBoard = move.applyPostconditions(gameBoard);
            }
        }else if(input.equals("Push"))
        {
            String[] params = ui.getTwoParams();
            Push push = new Push(params[0], params[1]);
            if(push.checkPreconditions(gameBoard))
            {
                gameBoard = push.applyPostconditions(gameBoard);
            }
        }else if(input.equals("ClimbUp"))
        {
            String param = ui.getOneParam();
            ClimbUp climbUp = new ClimbUp(param);
            if(climbUp.checkPreconditions(gameBoard))
            {
                gameBoard = climbUp.applyPostconditions(gameBoard);
            }
        }else if(input.equals("ClimbDown"))
        {
            String param = ui.getOneParam();
            ClimbDown climbDown = new ClimbDown(param);
            if(climbDown.checkPreconditions(gameBoard))
            {
                gameBoard = climbDown.applyPostconditions(gameBoard);
            }
        }else if(input.equals("Grab"))
        {
            String param = ui.getOneParam();
            Grab grab = new Grab(param);
            if(grab.checkPreconditions(gameBoard))
            {
                gameBoard = grab.applyPostconditions(gameBoard);
            }
        }
        return gameBoard;
    }
    
    public static GameBoard setItemLocations(GameBoard gameboard, boardPrinter printer, UI ui)
    {
        List<String> roomNames = Arrays.asList("A", "B", "C");

        for(int i = 0; i < gameboard.getItemNameList().size(); i++)
        {
            String input = null;
            while(ui.verifyInput(input, roomNames) == false)
            {
                input = ui.promptRoomforObject(gameboard.getItemNameList().get(i));
            }
            System.out.println("Item: " + gameboard.getItemNameList().get(i) + " Room: " + input);
            gameboard.placeItem(input, gameboard.getItemNameList().get(i));
        }
        printer.printBoard(gameboard.getRooms());
        return gameboard;
    }
}