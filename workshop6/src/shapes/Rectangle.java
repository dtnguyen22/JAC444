package shapes;

public class Rectangle extends Parallelogram{
    private ShapeArea recArea = () -> this.getWidth()*this.getLength();
    public Rectangle(double width, double length) throws RectangleException, ParallelogramException {
        super(width,length);
    }

    public double getRectangleArea(){
        return this.recArea.getArea();
    }



    @Override
    public String toString(){
        return String.format("Rectangle {w=%s, l=%s}, perimeter = %g, area = %g", this.getWidth(), this.getLength(), this.perimeter(), this.getRectangleArea());
    }
}
