import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MeanEndAnalysis {
    private List<String> takenActions;
    private Grab grab;
    private Move move;
    private ClimbUp climbUp;
    private ClimbDown climbDown;
    private Push push;
    private GameBoard board;

    public MeanEndAnalysis(GameBoard board)
    {
        takenActions = new ArrayList<String>();
        this.board = board;
    }

    public GameBoard MeanEndAnalysis(){
        while(true)
        {
            if(stateOne(board) == true){
                if(stateTwo(board) == true){
                    if(stateThree(board) == true){
                        if(stateFour(board) == true){
                            break;
                        }else{
                            grab = new Grab(board.getRoomNameMonkeyIn());
                            if(grab.checkPreconditions(board))
                            {
                                takenActions.add("Grab()");
                                board = grab.applyPostconditions(board);
                            }
                        }
                    }else{
                        climbUp = new ClimbUp(board.getRoomNameMonkeyIn());
                        if(climbUp.checkPreconditions(board))
                        {
                            takenActions.add("ClimbUp()");
                            board = climbUp.applyPostconditions(board);
                        };
                    }
                }else{
                    push = new Push(board.getRoomNameMonkeyIn(), board.getRoomNameBananaIn());
                    if(push.checkPreconditions(board))
                    {
                        takenActions.add("Push("+board.getRoomNameMonkeyIn()+","+board.getRoomNameBananaIn()+")");
                        board = push.applyPostconditions(board);
                    }
                }
            }else{
                move = new Move(board.getRoomNameMonkeyIn(), board.getRoomNameBoxIn());
                if(move.checkPreconditions(board) == true)
                {
                    takenActions.add("Move("+board.getRoomNameMonkeyIn()+","+board.getRoomNameBoxIn()+")");
                    board = move.applyPostconditions(board);
                }else{
                    climbDown = new ClimbDown(board.getRoomNameMonkeyIn());
                    
                    if(climbDown.checkPreconditions(board))
                    {
                        climbDown.applyPostconditions(board);
                    }
                }
            }
        }
        return board;
    }

    public void printActions()
    {
        for(int i = 0; i < takenActions.size(); i++)
        {
            System.out.println(takenActions.get(i));
        }
    }

    private boolean stateOne(GameBoard board)
    {
        return board.getRoomNameMonkeyIn().equals(board.getRoomNameBoxIn());
    }

    private boolean stateTwo(GameBoard board)
    {
        return board.getRoomNameMonkeyIn().equals(board.getRoomNameBananaIn());
    }

    private boolean stateThree(GameBoard board)
    {
        return board.getMonkeyHeight().equals("HIGH");
    }

    private boolean stateFour(GameBoard board)
    {
        return board.monkeyHasBananas();
    }
}
