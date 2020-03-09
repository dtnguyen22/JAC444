import java.io.IOException;

public class Student implements Comparable<Student> {
    private String firstName;
    private String lastName;
    private String department;
    private double grades;


    public Student(String firstName, String lastName, double grades, String department) {
        try {
            if (firstName == null || lastName == null || department == null) {
                throw new Exception("lastName or firstName is wrong");
            }
            this.lastName = lastName;
            this.firstName = firstName;
            this.department = department;
            if (grades < 0 || grades > 100) {
                throw new Exception("lastName or firstName is wrong");
            }
            this.grades = grades;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public String getName() {
        return String.format("%-15s %-15s", this.firstName, this.lastName);
    }

    public String toString() {
        return String.format("%-12s %-12s %-10s %s", this.firstName, this.lastName, this.grades, this.department);
    }

    public boolean equals(Student otherStudent) {
        return (this.grades == otherStudent.grades);
    }

    @Override
    public int compareTo(Student o) {
        int cmpResult = this.getLastName().compareTo(o.getLastName());// compareTo of String class
        //if cmpResult==0 -> same lastName -> use firstName -> otherwise return cmpResult;
        return (cmpResult == 0) ? this.getFirstName().compareTo(o.getFirstName()) : cmpResult;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getGrades() {
        return grades;
    }

    public static void forFun(Student student) {
        System.out.println(student);
    }

    public void setGrades(double grades) {
        this.grades = grades;
    }


}
