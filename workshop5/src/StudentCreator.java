import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class StudentCreator {
    public static void add(String stdID, String stdFName, String stdLName, String stdCourses, String filename) {
        //read the input file and prepare an arraylist of student
        ArrayList<Student> stdArrayList= new ArrayList<Student>();
        try{
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student stdObject;
            while(fis.available() != 0){
                stdObject = (Student) ois.readObject();
                if(stdObject != null){
                    stdArrayList.add(stdObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: File is not exist");
        }
        try( FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            //courses from textarea, line by line
            String[] courses = stdCourses.split("\n");
            ArrayList<String> arrayListCourses = new ArrayList<String>(Arrays.asList(courses));
            int studentID = Integer.parseInt(stdID);
            //create a new studentObj and add it to the arrayList above
            Student stdObject = new Student(studentID, stdFName,stdLName,arrayListCourses);
            stdArrayList.add(stdObject);
            //write all the std obj to the file
            for(Student stdObj : stdArrayList){
                oos.writeObject(stdObj);
            }
            System.out.println("Your student info is saved");
        } catch (IOException e) {
            System.out.println("Error: something is wrong with your student info");;
        }

    }
}
