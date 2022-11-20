import java.util.ArrayList;

public class Move {
    private String operatoreName = "MOVE";
    private String moveFrom;
    private String moveTo;
    
    public Move(String from, String to)
    {
        this.moveFrom = from;
        this.moveTo = to;
    }

    public boolean checkPreconditions(GameBoard board)
    {
        if(!board.isMonkeyAt(moveFrom))
        {
            return false;
        }
        
        if(!board.getMonkeyHeight().equals("LOW"))
        {
            return false;
        }
        
        return true;
    }

    public GameBoard applyPostconditions(GameBoard board)
    {
        GameBoard postBoard = board.copyBoard();
        postBoard.moveMonkey(moveTo);
        return postBoard;
    }
}
