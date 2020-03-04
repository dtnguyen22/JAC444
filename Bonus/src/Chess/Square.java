package Chess;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.IOException;

public class Square extends Rectangle {
    private int x;
    private int y;
    private Piece piece;

    public Square(int x, int y) {
        super(80, 80, Color.TRANSPARENT);
        this.x = x;
        this.y = y;
    }

//    public boolean generatePiece(Square aSquare){
//        int pos = this.squares.indexOf(aSquare);
//        this.squares.get(pos).setFill(Color.RED);
//        return true;
//    }

    public int get_X() {
        return x;
    }

    public int get_Y() {
        return y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //
    public boolean isOccupied() {
        return this.piece != null;
    }

    public void setSquareWith(Piece aPiece) {
        if (!this.isOccupied()) {
            System.out.println("set");
            this.setPiece(aPiece);
            try (FileInputStream fis = new FileInputStream(aPiece.PIC_URL)) {
                Image img = new Image(fis);
                this.setFill(new ImagePattern(img));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            System.out.println("nope");
        }
    }


}
