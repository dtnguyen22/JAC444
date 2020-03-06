package Chess;

import java.util.*;

public class Move {
    private Piece piece;
    private LinkedList<Square> moves;

    public Move(Piece aPiece) {
        this.piece = aPiece;
        this.moves = new LinkedList<>();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece aPiece) {
        this.piece = aPiece;
    }

    public LinkedList<Square> getMoves() {
        return this.moves;
    }

}