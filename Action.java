public interface Action {
    public boolean checkPreconditions(GameBoard board);

    public GameBoard applyPostconditions(GameBoard board);
}
