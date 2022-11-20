public class ClimbDown {
    private String operatorName = "ClimbDown";
    private String from;

    public ClimbDown(String from)
    {
        this.from = from;
    }

    public boolean checkPreconditions(GameBoard board)
    {
        if(!board.isMonkeyAt(from))
        {
            return false;
        }
        if(!board.getMonkeyHeight().equals("HIGH"))
        {
            return false;
        }
        if(!board.isBoxAt(from))
        {
            return false;
        }
        return true;
    }

    public GameBoard applyPostConditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        post.setMonkeyHeight("LOW");
        return post;
    }
}