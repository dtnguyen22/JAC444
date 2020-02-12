import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentCreator {
    public static void main(String[] args)  {
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Your stdID: ");
            int stdID = in.nextInt();
            System.out.println("Your firstname: ");
            String firstName = in.nextLine();
            System.out.println("Your lastname: ");
            String lastName = in.nextLine();
            String aCourse;
            ArrayList<String> courses = new ArrayList<String>();
            do{
                System.out.println("Your courses, 0 to finish: ");
                aCourse = in.nextLine();
                if(aCourse.equals("0") == false){
                    courses.add(aCourse);
                }
            }while(aCourse != "0");
            Student stdObject = new Student(stdID, firstName, lastName, courses);
            FileOutputStream fos = new FileOutputStream("file.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stdObject);
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
