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
        try (BufferedReader br = new BufferedReader(new FileReader("src/shapes/shapes.txt"))) {
            while ((s = br.readLine()) != null) {
                try {
                    String[] tokens = s.split(",");
                    for (int i=1; i<tokens.length; i++){
                        double aToken = new Double(tokens[i]);
                        if(aToken <= 0){
                            throw new Exception("Main - Invalid side!" + tokens[0] + tokens[1]);
                        }
                    }
                    if (tokens[0].equals("Circle") ) {
                        shapes[count] = new Circle(new Double(tokens[1]));
                    } else if (tokens[0].equals("Triangle") ) {
                        shapes[count] = new Triangle(new Double(tokens[1]), new Double(tokens[2]), new Double(tokens[3]));
                    } else if (tokens[0].equals("Parallelogram") ) {
                        shapes[count] = new Parallelogram(new Double(tokens[1]), new Double(tokens[2]));
                    } else if (tokens[0].equals("Rectangle") ) {
                        shapes[count] = new Rectangle(new Double(tokens[1]), new Double(tokens[2]));
                    } else if (tokens[0].equals("Square") ) {
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
    }
}
