package shapes;
import java.lang.Math;
public class Circle implements Shape {
    private double radius;
    //workshop 6
    private ShapeArea<Circle> circleArea = aCircle-> aCircle.radius*aCircle.radius*Math.PI; //return a double

    //constructor
    public Circle(double radius) throws CircleException {
        if (isValid(radius)) {
            this.radius = radius;
        } else {
            throw new CircleException("Circle - Invalid radius!");
        }
    }

    //workshop6
    public double getCircleArea(){
        return circleArea.getArea(this);
    }


    public boolean isValid(double radius){
        if(radius > 0 ){
            return true;
        }else{
            return false;
        }
    }

    //get radius
    public double getRadius() {
        return radius;
    }

    //set radius, if r < 0 throw exception
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
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
