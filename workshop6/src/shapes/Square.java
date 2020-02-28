package shapes;

public class Square extends Rectangle {
    private ShapeArea<Square> squareArea = aSquare -> this.getWidth()*this.getWidth(); //

    public Square(double side) throws SquareException, RectangleException, ParallelogramException {
        super(side,side);
    }

    public double getSquareArea(){
       return this.squareArea.getArea(this);
    }

    @Override
    public String toString(){
        return String.format("Square {s=%s}", this.getWidth());
    }
}
