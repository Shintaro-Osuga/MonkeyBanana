public class Push {
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
        System.out.println("true-------");
        return true;
    }

    public GameBoard applyPostConditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        post.moveMonkey(pushTo);
        post.moveBox(pushTo);
        return post;
    }
}
