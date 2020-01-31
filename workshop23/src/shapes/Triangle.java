package shapes;

public class Triangle implements Shape {
    private double sideOne;
    private double sideTwo;
    private double sideThree;

    //constructor

    public boolean isTriangle(double a, double b, double c){
        if((a+b) <= c || (a+c)<= b || (b+c)<=a){
            return false;
        }
        return true;
    }
    public Triangle(double sideOne, double sideTwo, double sideThree) throws TriangleException{
        if(sideOne > 0 && sideTwo > 0 && sideThree > 0
                && isTriangle(sideOne, sideTwo, sideThree) == true){
            this.sideOne = sideOne;
            this.sideTwo = sideTwo;
            this.sideThree = sideThree;
        }else{
            throw new TriangleException(String.format("Triangle %s %s %s - Invalid side!", sideOne, sideTwo, sideThree));
        }
    }

    //getters and setters
    public double getSideOne() {
        return sideOne;
    }

    public void setSideOne(double sideOne) throws TriangleException {
        if(sideOne > 0){
            this.sideOne = sideOne;
        }else{
            throw new TriangleException("Tri - Invalid sideOne length!");
        }
    }

    public double getSideTwo() {
        return sideTwo;
    }

    public void setSideTwo(double sideTwo) throws TriangleException {
        if(sideTwo > 0){
            this.sideTwo = sideTwo;
        }else{
            throw new TriangleException("Tri - Invalid sideTwo length!");
        }
    }

    public double getSideThree() {
        return sideThree;
    }

    public void setSideThree(double sideThree) throws TriangleException {
        if(sideThree > 0){
            this.sideThree = sideThree;
        }else{
            throw new TriangleException("Tri - Invalid sideThree length!");
        }
    }

    //returns a triangle perimeter
    @Override
    public double perimeter(){
        return this.sideOne+this.sideTwo+this.sideThree;
    }

    //override toString
    @Override
    public String toString(){
        return String.format("Triangle {s1=%s, s2=%s, s3=%s}", this.getSideOne(), this.getSideTwo(), this.getSideThree());
    }
}
