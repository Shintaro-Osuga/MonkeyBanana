public class Push implements Action{
    private String operatorName = "PUSH";
    private String pushFrom;
    private String pushTo;

    public Push(String from, String to)
    {
        pushFrom = from;
        pushTo = to;
    }

    public boolean checkPreconditions(GameBoard board)
    {
        if(!board.isMonkeyAt(pushFrom))
        {
            return false;
        }
        if(!board.getMonkeyHeight().equals("LOW"))
        {
            return false;
        }
        if(!board.isBoxAt(pushFrom))
        {
            return false;
        }
        return true;
    }

    public GameBoard applyPostconditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        post.moveMonkey(pushTo);
        post.moveBox(pushTo);
        return post;
    }
}
