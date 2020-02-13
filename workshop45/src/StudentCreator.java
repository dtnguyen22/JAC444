import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentCreator {
    public static void main(String[] args) {
        try( FileOutputStream fos = new FileOutputStream("file.out");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            while (true) {
                Scanner in = new Scanner(System.in);
                System.out.println("Do you want to add a student info?(y to continue) ");
                String ans = in.nextLine();
                if (ans.equals("y")) {
                    try {
                        System.out.println("Your stdID: ");
                        int stdID = Integer.parseInt(in.nextLine());//Number format exception
                        System.out.println("Your firstname: ");
                        String firstName = in.nextLine();
                        System.out.println("Your lastname: ");
                        String lastName = in.nextLine();
                        String aCourse;
                        ArrayList<String> courses = new ArrayList<String>();
                        do {
                            System.out.println("Your courses, 0 to finish: ");
                            aCourse = in.nextLine();
                            if( aCourse != null && !aCourse.equals("0")){
                                courses.add(aCourse);
                            }
                        } while (!aCourse.equals("0"));
                        Student stdObject = new Student(stdID, firstName, lastName, courses);
                        oos.writeObject(stdObject); // throws IOException
                        System.out.println("Your student info is saved!");
                    } catch (IOException e) {
                        System.out.println("Something is wrong with IO stream" + e);
                    } catch (NumberFormatException e){
                        System.out.println("Something is wrong with your stdID " + e);
                    }
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
