public class ClimbUp {
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

    public GameBoard applyPostConditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        System.out.println("Monkey is high");
        post.setMonkeyHeight("HIGH");
        return post;
    }
}