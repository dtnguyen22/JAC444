package shapes;

public class Square extends Rectangle {
    private ShapeArea squareArea = () -> this.getWidth()*this.getWidth(); //

    public Square(double side) throws SquareException, RectangleException, ParallelogramException {
        super(side,side);
    }

    public double getSquareArea(){
       return this.squareArea.getArea();
    }

    @Override
    public String toString(){
        return String.format("Square {s=%s} perimeter = %g, area = %g", this.getWidth(), this.perimeter(), this.getSquareArea());
    }
}
