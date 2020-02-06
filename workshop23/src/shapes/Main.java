package shapes;

import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void printResult(Shape[] shapes){
        for (Shape aShape : shapes){
            if(aShape != null){
                System.out.printf("%s perimeter = %g \n", aShape, aShape.perimeter());
            }
        }
    }
    public static void main(String[] args) throws CircleException, TriangleException {
        //task 1
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
        }
        //printing results




        //task 2
        // delete cicles with maximum perimeter
        double max = 0;
        for (Shape cir : shapes){ //looking for the circle(s) which has the maximum perimeter
            if(cir instanceof Circle){
                if(cir.perimeter() > max){
                    max = cir.perimeter();
                }
            }
        }
        for(int i = 0; i< numOfShape; i++){
            if(shapes[i] instanceof Circle){
                if(shapes[i].perimeter() == max){
                    shapes[i] = null;
                }
            }
        }
        //remove Smallest Triangle
        double min = 999999999;
        for (Shape tri : shapes){ //looking for the triangle(s) which has the minimum perimeter
            if(tri instanceof Triangle){
                if(tri.perimeter() < min){
                    min = tri.perimeter();
                }
            }
        }
        for(int i = 0; i< numOfShape; i++){
            if(shapes[i] instanceof Triangle){
                if(shapes[i].perimeter() == min){
                    shapes[i] = null;
                }
            }
        }


        System.out.printf("%s shapes were created:\n", numOfShape);
        printResult(shapes);
        //task 3
        double totalParallelogramPeri = 0;
        double totalTrianglePeri = 0;
//        for(int i = 0; i < shapes.length; i++){
//            if(shapes[i]!=null){
//                if(String.format(shapes[i].getClass().getSimpleName()).equals("Parallelogram")){
//                    totalParallelogramPeri+=shapes[i].perimeter();
//                }
//            }
//        }
        for(Shape para : shapes){
            if(para != null){
               if(String.format(para.getClass().getSimpleName()).equals("Parallelogram")){
                  totalParallelogramPeri+=para.perimeter();
                }
            }
        }
        for (Shape tri : shapes){ //looking for the triangle(s) which has the minimum perimeter
            if(tri instanceof Triangle){
                totalTrianglePeri+=tri.perimeter();
            }
        }
        System.out.printf("Total perimeter of Parallelogram is: %s\n", totalParallelogramPeri);
        System.out.printf("Total perimeter of Triangle is: %s\n", totalTrianglePeri);

    }
}
