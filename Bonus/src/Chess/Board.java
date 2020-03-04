package Chess;



import java.util.ArrayList;
import java.util.List;
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


}
