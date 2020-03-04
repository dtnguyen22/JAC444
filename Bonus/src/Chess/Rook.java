package Chess;

public class Rook extends Piece {

    public Rook() {
        this.name = "Rook";
    }

    @Override
    boolean move() {
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
