package Chess;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Square {
    private int x;
    private int y;
    private Image image; //depends

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setImage() throws FileNotFoundException {
        FileInputStream fis = new FileInputStream("src\\resources\\bknight.png");
        Image image = new Image(fis);
    }
}
