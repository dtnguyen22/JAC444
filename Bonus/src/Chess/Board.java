package Chess;


import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.List;
import java.util.Set;

public class Board {
    private final int maxVertical = 8;
    private final int maxHorizontal = 8;
    private List<Square> squares;

    public Board() {
    }

    public List<Square> getSquares() {
        return squares;
    }

    public Square getSquare(int x, int y) {
        //even though stream returns a list, all the square has different x and y position
        return (Square) squares.stream().filter(aSquare ->  aSquare.getX() == x && aSquare.getY()==y);
    }
}
