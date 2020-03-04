package Chess;

public abstract class Piece {
    protected Square position;
    protected String name;
    protected String PIC_URL;
    public abstract boolean move();
    public abstract void getLegalMoves(Board board);

    public Square getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}
