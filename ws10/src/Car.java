import java.io.*;

public class Car implements Serializable {

    private String model;
    private String color;
    private double mileage;
    private String plate;

    public Car( String model, String color, double mileage ) {
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.plate = "unregistered";
    }

    public String toString() {
        return "Model: " + model + " Color: " + color +
                " Mileage: " +  mileage +
                " Plate: " + plate;
    }

    public void getRegistered(String plate) {
        this.plate = plate;
    }
}