package Chess;

import java.util.HashMap;
import java.util.Map;

public class Move {
    private Piece piece;
    private Map<Square, Square> movementTracker = new HashMap<>();

    public Move(Piece aPiece, Square currentPos, Square nextPos){
        this.piece = aPiece;
        this.movementTracker.put(currentPos, nextPos);
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece aPiece) {
        this.piece = aPiece;
    }

    public Map<Square, Square> getMovementTracker() {
        return this.movementTracker;
    }

    public String getString(Square aSquare) {
        return this.movementTracker.get(aSquare).toString();
    }
}
