package Chess;


import java.util.Set;
import java.util.TreeSet;

public class Board  {
    private final int maxVertical = 8;
    private final int maxHorizontal = 8;
    private TreeSet<Square> squares;
    public Board(){

    }

    public Set<Square> getSquares() {
        return squares;
    }
}
