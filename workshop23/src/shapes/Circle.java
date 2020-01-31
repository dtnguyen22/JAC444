package shapes;

public class Circle implements Shape {
    private final double PI = 3.14;
    private double radius;

    //constructor
    public Circle(double radius) throws CircleException {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new CircleException("Invalid radius!\n");
        }
    }

    //get radius
    public double getRadius() {
        return radius;
    }

    //set radius, if r < 0 throw exception
    public void setRadius(double radius) throws CircleException {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new CircleException("Invalid radius!\n");
        }
    }

    //this func returns a circle's perimeter
    @Override
    public double perimeter() {
        return this.radius * 2 * PI;
    }


    //override toString
    @Override
    public String toString() {
        return "Circle {r=" + this.radius + "}";
    }
}
