package Chess;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
    private int x;
    private int y;
    private Piece aPiece;

    public Square(int x, int y) {
        super(80, 80, Color.TRANSPARENT);
        this.x = x;
        this.y = y;
    }


}
