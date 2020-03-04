package Chess;

public abstract class Piece {
    protected int x;
    protected int y;
    protected String name;
    protected String PIC_URL;
    public abstract boolean move();
    public abstract void getLegalMoves(Board board);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return this.name;
    }
}
