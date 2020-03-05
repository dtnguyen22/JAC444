package shapes;

public class Triangle implements Shape {
    private double sideOne;
    private double sideTwo;
    private double sideThree;

    //constructor
    public Triangle(double sideOne, double sideTwo, double sideThree) throws TriangleException{
        if(sideOne > 0 && sideTwo > 0 && sideThree > 0
                && isValid(sideOne, sideTwo, sideThree) == true){
            this.sideOne = sideOne;
            this.sideTwo = sideTwo;
            this.sideThree = sideThree;
        }else{
            throw new TriangleException("Triangle - Invalid side(s)!");
        }
    }

    public boolean isValid(double a, double b, double c){
        if((a+b) <= c || (a+c)<= b || (b+c)<=a){
            return false;
        }
        return true;
    }

    //getters and setters
    public double getSideOne() {
        return sideOne;
    }

    public void setSideOne(double sideOne) throws TriangleException {
        if(sideOne > 0){
            this.sideOne = sideOne;
        }
    }

    public double getSideTwo() {
        return sideTwo;
    }

    public void setSideTwo(double sideTwo) throws TriangleException {
        if(sideTwo > 0){
            this.sideTwo = sideTwo;
        }
    }

    public double getSideThree() {
        return sideThree;
    }

    public void setSideThree(double sideThree) throws TriangleException {
        if(sideThree > 0){
            this.sideThree = sideThree;
        }
    }

    //returns a triangle perimeter
    @Override
    public double perimeter(){
        double peri = this.sideOne+this.sideTwo+this.sideThree;
        return peri;
    }

    //override toString
    @Override
    public String toString(){
        return String.format("Triangle {s1=%s, s2=%s, s3=%s} perimeter = %g", this.getSideOne(), this.getSideTwo(), this.getSideThree(), this.perimeter());
    }
}
