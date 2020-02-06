package shapes;
import java.lang.Math;
public class Circle implements Shape {
    private double radius;

    //constructor
    public Circle(double radius) throws CircleException {
        if (radius > 0) {
            this.radius = radius;
        } else {
            throw new CircleException("Circle - Invalid radius!");
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
            throw new CircleException("Circle - Invalid radius!");
        }
    }

    //this func returns a circle's perimeter
    @Override
    public double perimeter() {
        return this.radius * 2 * Math.PI;
    }


    //override toString
    @Override
    public String toString() {
        return String.format("Circle{r=%s}", this.getRadius());
    }
}
