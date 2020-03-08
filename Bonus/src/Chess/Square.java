package Chess;


import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.IOException;

public class Square extends Label {
    private int x;
    private int y;
    private Piece piece;

    public Square(int x, int y) {
        this.setAlignment(Pos.CENTER); //for text and image alignment
        this.setFont(Font.font(48));
        this.setMinWidth(80);
        this.setMinHeight(80);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return this.piece != null;
    }

    public boolean setSquareWith(Piece aPiece) {
        if (!this.isOccupied()) {
            System.out.println("set");
            this.setPiece(aPiece);
            try (FileInputStream fis = new FileInputStream(aPiece.PIC_URL)) {
                Image img = new Image(fis);
                this.setGraphic(new ImageView(img));

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return true;
        }else{
            System.out.println("nope");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
