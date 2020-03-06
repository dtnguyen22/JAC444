package Chess;

public abstract class Piece {
    protected Square position;
    protected String PIC_URL;
    public abstract boolean move();
    public abstract Move getPossibleMoves(Board board);

    public Square getPosition() {
        return position;
    }

    public void setPosition(Square position) {
        this.position = position;
    }


}
