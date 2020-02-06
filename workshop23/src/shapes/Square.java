package shapes;

public class Square extends Rectangle {
    public Square(double side) throws SquareException, RectangleException, ParallelogramException {
        super(side,side);
    }

    @Override
    public String toString(){
        return String.format("Square {s=%s}", this.getWidth());
    }
}
