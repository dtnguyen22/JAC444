import java.util.ArrayList;

public class Student {
    private int stdID;
    private String firstName;
    private String lastName;
    private ArrayList<String> courses;

    public Student(){
    }

    public Student(int stdID, String firstName, String lastName, ArrayList<String> courses) {
        this.stdID = stdID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    @Override
    public String toString(){
        return String.format("Student: %s\n Name: %s %s\n Courses: %s", this.stdID, this.firstName, this.lastName, this.courses);
    }

    public int getStdID() {
        return stdID;
    }

    public void setStdID(int stdID) {
        this.stdID = stdID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}