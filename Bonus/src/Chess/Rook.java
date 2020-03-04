package Chess;

public class Rook extends Piece {

    public Rook() {
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
