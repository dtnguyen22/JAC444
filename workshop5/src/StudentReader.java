import java.io.*;
import javafx.stage.Stage;

class StudentReader {

    public static void display(String filename) {
        try(FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);){
            Student stdObject;
            while(fis.available() != 0){
                stdObject = (Student) ois.readObject();
                if(stdObject != null){
                    System.out.println(stdObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: File is not exist");
        }
    }


}
