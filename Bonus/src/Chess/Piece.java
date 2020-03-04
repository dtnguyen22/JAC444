package Chess;

public abstract class Piece {
    protected String name;
    protected String PIC_URL = "src/resources/";
    abstract boolean move();
    abstract String getName();
}
