package Chess;

public class Knight extends Piece {
    //a piece should know where it is located
    public Knight(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "Knight";
        this.PIC_URL = "src/resources/knight.png";
    }



    @Override
    public void getLegalMoves(Board board) {
    }

    @Override
    public boolean move() {
        return false;
    }

}
