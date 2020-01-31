package shapes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String s;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/shapes/shapes.txt"))) {
            while ((s = br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Shape[] shapes = new Shape[count];
        count = 0;
        int validShapes = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/shapes/shapes.txt"))) {
            while ((s = br.readLine()) != null) {
                try {
                    String[] tokens = s.split(",");
                    //if the first token is not in the shape list and the number of token is not match->ignore
                    if (tokens[0].equals("Circle") && tokens.length == 2) {
                        shapes[count] = new Circle(new Double(tokens[1]));
                    } else if (tokens[0].equals("Triangle") && tokens.length == 4) {
                        shapes[count] = new Triangle(new Double(tokens[1]), new Double(tokens[2]), new Double(tokens[3]));
                    } else if (tokens[0].equals("Parallelogram") && tokens.length == 3) {
                        shapes[count] = new Parallelogram(new Double(tokens[1]), new Double(tokens[2]));
                    } else if (tokens[0].equals("Rectangle") && tokens.length == 3 ) {
                        shapes[count] = new Rectangle(new Double(tokens[1]), new Double(tokens[2]));
                    } else if (tokens[0].equals("Square") && tokens.length == 2) {
                        shapes[count] = new Square(new Double(tokens[1]));
                    } else {
                        continue;
                    }
                    count++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(String.format("%s shapes were created:", count));
        for (Shape aShape : shapes){
            if (aShape instanceof Shape){
                System.out.println(String.format("%s perimeter = %g", aShape, aShape.perimeter()));
            }
        }

    }
}
