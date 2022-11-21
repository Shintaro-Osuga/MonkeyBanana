public class ClimbUp implements Action{
    private String operatorName = "ClimbUp";
    private String from;

    public ClimbUp(String from)
    {
        this.from = from;
    }

    public boolean checkPreconditions(GameBoard board)
    {
        if(!board.isMonkeyAt(from))
        {
            return false;
        }
        if(!board.getMonkeyHeight().equals("LOW"))
        {
            return false;
        }
        if(!board.isBoxAt(from))
        {
            return false;
        }
        return true;
    }

    public GameBoard applyPostconditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        post.setMonkeyHeight("HIGH");
        return post;
    }
}