package shapes;

public class Rectangle extends Parallelogram{

    public Rectangle(double width, double length) throws RectangleException, ParallelogramException {
        super(width,length);
    }

    @Override
    public double perimeter() {
        return super.perimeter();
    }

    @Override
    public String toString(){
        return String.format("Rectangle {w=%s, l=%s}", this.getWidth(), this.getLength());
    }
}
