package shapes;

public class Parallelogram implements Shape {
    private double width;
    private double length;
    //private double height;

    public Parallelogram(double width, double length) throws ParallelogramException{
        if(isValid(width,length) == true){
            this.width = width;
            this.length = length;
        }else{
            throw new ParallelogramException("Invalid side!");
        }
    }

    public boolean isValid(double width, double length) {
        if (width > 0 && length > 0) {
            return true;
        }else{
            return false;
        }
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public void setWidth(double width) throws ParallelogramException {
        if(width > 0){
            this.width = width;
        }
    }


    public void setLength(double length) throws ParallelogramException {
        if(width > 0){
            this.length = length;
        }
    }

    @Override
    public double perimeter(){
        return (this.width+this.length)*2;
    }
    @Override
    public String toString(){
        return String.format("Parallelogram {w=%s, h=%s}, perimeter = %g", this.getWidth(), this.getLength(), this.perimeter());
    }
}
