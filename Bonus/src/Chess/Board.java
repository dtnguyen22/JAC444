package Chess;


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Board {
    private final int maxVertical = 8;
    private final int maxHorizontal = 8;
    private List<Square> squares;


    public Board() {
        this.squares = new ArrayList<>();
    }

    public List<Square> getSquares() {
        return squares;
    }


    public boolean addPieceToBoard(Piece aPiece, Square aSquare){
        int pos = this.squares.indexOf(aSquare);
        if(this.squares.get(pos).setSquareWith(aPiece)){
            aSquare.getPiece().getPossibleMoves(this);
        }
        return true;
    }

    public boolean isOccupiedBy(Square aSquare){
        Optional<Square> res = this.squares
                .stream()
                .filter(current-> (current.get_X() == aSquare.get_X() && current.get_Y()==aSquare.get_Y() && current.isOccupied()))
                .findFirst();
        if(res.isPresent()){
            System.out.println(aSquare + " is occupied");
        }
        return res.isPresent();
    }

}
