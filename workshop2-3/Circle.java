package workshop2;
public class Circle implements Shape{
	private double radius;
	private final double PI = 3.14;
	
	
	public Circle(double radius) throws CircleException{
		if(radius > 0){
			this.radius = radius;
		}else{
			throw new CircleException("Invalid radius!");
		}
	}
	
	@Override
	public double perimeter(){
		return this.radius*2*PI;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) throws CircleException {
		if(radius > 0){
			this.radius = radius;
		}else{
			throw new CircleException("Invalid radidus!");
		}
	}
	
	@Override
	public String toString(){
		return "Circle {r=" + getRadius() + "}";
	}
}
