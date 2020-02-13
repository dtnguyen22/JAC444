import java.io.*;

public class StudentReader {
    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream("file.out");
            ObjectInputStream ois = new ObjectInputStream(fis);){
            Student stdObject;
            while(fis.available() != 0){
                stdObject = (Student) ois.readObject();
                if(stdObject != null){
                    System.out.println(stdObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
