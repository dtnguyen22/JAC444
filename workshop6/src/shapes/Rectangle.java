package shapes;

public class Rectangle extends Parallelogram{
    private ShapeArea<Rectangle> recArea = aRec -> this.getWidth()*this.getLength();
    public Rectangle(double width, double length) throws RectangleException, ParallelogramException {
        super(width,length);
    }

    public double getRectangleArea(){
        return this.recArea.getArea(this);
    }



    @Override
    public String toString(){
        return String.format("Rectangle {w=%s, l=%s}", this.getWidth(), this.getLength());
    }
}
