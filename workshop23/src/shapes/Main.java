package shapes;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    //printing out all the shapes
    public static void printShapes(Shape[] arrayShape){
        for (Shape aShape : arrayShape){
                System.out.printf("%s perimeter = %g \n", aShape, aShape.perimeter());
        }
    }
    //remove biggest circle(s) from shapes array
    public static void removeBiggestCircle(Shape[] shapes){
        double max = 0;
        double element = shapes.length;
        for (Shape cir : shapes){ //looking for the circle(s) which has the maximum perimeter
            if(cir instanceof Circle){
                if(cir.perimeter() > max){
                    max = cir.perimeter();
                }
            }
        }

        for (int i = 0;i < shapes.length; i++){
            if(shapes[i].perimeter() == max){
                if(shapes[i] instanceof Circle){
                    for(int j = i; j < shapes.length - i; j++){
                        shapes[i] = shapes[i+1];
                    }
                }
            }
        }
    }

    public static void removeSmallestTriangle(Shape[] shapes){
        double min = 0;
        for (Shape tri : shapes){ //looking for the circle(s) which has the maximum perimeter
            if(tri instanceof Triangle){
                if(min == 0){
                    min = tri.perimeter();
                }
                if(tri.perimeter() < min){
                    min = tri.perimeter();
                }
            }
        }
        for(Shape tri : shapes){ //remove it from the array
            if(tri instanceof Triangle){
                if(tri.perimeter() == min){
                    tri = null;
                }
            }
        }
    }

    public static void main(String[] args) {
        String s;
        int numOfShape = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/shapes/shapes.txt"))) {
            while ((s = br.readLine()) != null) {
                numOfShape++; //count number of line in this file
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Shape[] shapes = new Shape[numOfShape];
        numOfShape = 0; //reset counter
        int validShapes = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/shapes/shapes.txt"))) {
            while ((s = br.readLine()) != null) {
                try {
                    String[] tokens = s.split(",");
                    //if the first token is not in the shape list and the number of token is not match->ignore
                    if (tokens[0].equals("Circle") && tokens.length == 2) {
                        shapes[numOfShape] = new Circle(Double.parseDouble(tokens[1]));
                    } else if (tokens[0].equals("Triangle") && tokens.length == 4) {
                        shapes[numOfShape] = new Triangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    } else if (tokens[0].equals("Parallelogram") && tokens.length == 3) {
                        shapes[numOfShape] = new Parallelogram(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                    } else if (tokens[0].equals("Rectangle") && tokens.length == 3 ) {
                        shapes[numOfShape] = new Rectangle(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]));
                    } else if (tokens[0].equals("Square") && tokens.length == 2) {
                        shapes[numOfShape] = new Square(Double.parseDouble(tokens[1]));
                    } else {
                        continue;//it will not come to numOfShape++ and continue the loop
                    }
                    numOfShape++;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // the problem is there are unfilled elements in the shapes array
            // we create a new array that fits the number of element in shapes array
            Shape[] shapeList = new Shape[numOfShape];
            for(int i  = 0 ; i < numOfShape; i++){
                shapeList[i] = shapes[i];
            }
            shapes = shapeList;
        }
        //task 1
        System.out.printf("%s shapes were created:\n", shapes.length);
       // printShapes(shapes);
//        System.out.println(numOfShape);
          removeBiggestCircle(shapes);
//        removeSmallestTriangle(shapes);
//        printShapes(shapes);

    }
}
