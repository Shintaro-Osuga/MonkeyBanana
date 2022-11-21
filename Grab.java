public class Grab implements Action{
    private String operatorName = "Grab";
    private String from;

    public Grab(String from)
    {
        this.from = from;
    }

    public boolean checkPreconditions(GameBoard board)
    {
        if(!board.isMonkeyAt(from))
        {
            return false;
        }
        if(!board.isBananasAt(from))
        {
            return false;
        }
        if(!board.getMonkeyHeight().equals("HIGH"))
        {
            return false;
        }
        return true;
    }

    public GameBoard applyPostconditions(GameBoard board)
    {
        GameBoard post = board.copyBoard();
        post.setmonkeyHasBananas(true);
        return post;
    }
}
