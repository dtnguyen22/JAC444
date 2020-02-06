package shapes;

public class Parallelogram implements Shape {
    private double width;
    private double length;
    //private double height;

    public Parallelogram(){
        this.width = 0;
        this.length = 0;
    }

    public Parallelogram(double width, double length) throws ParallelogramException{
        if(isValid(width,length) == true){
            this.width = width;
            this.length = length;
        }else{
            throw new ParallelogramException("Invalid side!");
        }
    }

    @Override
    public boolean isValid() {
        return isValid(this.width, this.length);
    }

    //overloading
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
        return "Parallelogram {w="+this.width+", h="+this.length+"} ";
    }
}
