package Chess;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Knight extends Piece {
    private Move possibleMove ;

    //a piece should know where it is located
    public Knight(Square aSquare) {
        this.possibleMove = new Move(this);
        this.position = aSquare;
        this.PIC_URL = "src/resources/knight.png";
    }

    @Override
    public Move getPossibleMoves(Board board) {
        int[] possibleX = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] possibleY = {-1, -2, -2, -1, 1, 2, 2, 1};
        for (int i = 0; i < 8; i++) {
            int tmpX = possibleX[i] + this.position.get_X();
            int tmpY = possibleY[i] + this.position.get_Y();
            if (tmpX >= 0 && tmpX < 8) {
                if (tmpY >= 0 && tmpY < 8) {
                    //if there
                    Square tmpSquare = board.getSquareByXAndY(tmpX, tmpY);
                    if(tmpSquare != null && !board.isOccupied(tmpSquare)){
                        this.possibleMove.getMoves().add(tmpSquare);
                    }
                }
            }
        }
        this.possibleMove.getMoves().forEach((System.out::println));
        return this.possibleMove;
    }


    @Override
    public boolean move() {
        return false;
    }

}
