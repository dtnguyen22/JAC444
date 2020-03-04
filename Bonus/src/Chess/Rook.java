package Chess;

public class Rook extends Piece {

    public Rook() {
        this.x = x;
        this.y = y;
        this.name = "Rook";
        this.PIC_URL = "src/resources/rook.png";

    }

    @Override
    public boolean move() {
        return false;
    }

    @Override
    public void getLegalMoves(Board board) {
    }
}
