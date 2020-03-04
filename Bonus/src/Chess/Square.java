package Chess;


import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Square extends Rectangle {
    private int x;
    private int y;
    private Piece aPiece;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getPosX() {
        return x;
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public int getPosY() {
        return y;
    }

    public void setPosY(int y) {
        this.y = y;
    }

    public boolean getImage(Piece piece) throws FileNotFoundException {
        String pieceName = piece.toString();
        FileInputStream fis = new FileInputStream("/src/resources/knight.png");
        return true;
    }
}
