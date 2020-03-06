package Chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private List<Move> possibleMove = new ArrayList<>();
    private List<Move> moveTracker = new ArrayList<>();

    //a piece should know where it is located
    public Knight(Square aSquare) {
        this.moveTracker = new ArrayList<>();
        this.moveTracker = new ArrayList<>();
        this.position = aSquare;
        this.PIC_URL = "src/resources/knight.png";
    }

    @Override
    public void getPossibleMoves(Board board) {
        int[] possibleX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] possibleY = {-1, -2, -2, -1, 1, 2, 2, 1};
        for (int i = 0; i < 8; i++) {
            int tmpX = possibleX[i] + this.position.get_X();
            int tmpY = possibleY[i] + this.position.get_Y();
            if (tmpX >= 0 && tmpX < 8) {
                if (tmpY >= 0 && tmpY < 8) {
                    //if there
                    Square tmpSquare = new Square(tmpX, tmpY);
                    Move aMove = new Move(this, this.position, tmpSquare);
                    this.possibleMove.add(aMove);
                }
            }
        }
        for(Move move: possibleMove){
            System.out.println(move.getString(this.position));
        }
    }


    @Override
    public boolean move() {
        return false;
    }

}
