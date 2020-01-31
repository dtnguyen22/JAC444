package shapes;

public class Rectangle extends Parallelogram{

    public Rectangle(double width, double length) throws ParallelogramException {
        super(width,length);
    }

    @Override
    public String toString(){
        return String.format("Rectangle {w=%s, l=%s}", this.getWidth(), this.getLength());
    }
}
