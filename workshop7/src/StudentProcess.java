import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.util.Pair;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class StudentProcess {


    public static void main(String[] args) throws Exception {
        Student[] students = {
                new Student("Jack", "Smith", 50.0, "IT"),
                new Student("Aaron", "Johnson", 76.0, "IT"),
                new Student("Maria", "White", 35.8, "Business"),
                new Student("John", "White", 47.0, "Media"),
                new Student("Laney", "White", 62.0, "IT"),
                new Student("Jack", "Jones", 32.9, "Business"),
                new Student("Wesley", "Jones", 42.89, "Media")
        };

        /*Task 1*/
        List<Student> listStudent = Arrays.asList(students);
        // ArrayList class implements forEach function to iterate through the list
        System.out.println("Task 1 - Complete Student list: ");
        listStudent.forEach(System.out::println);  // uncomment to run
        System.out.println();
        /*------*/

        /*Task 2*/
        List<Student> list50to100SortedStudent = listStudent.stream()
                .filter(aStudent -> (aStudent.getGrades() >= 50 && aStudent.getGrades() <= 100))
                .sorted(Comparator.comparing(Student::getGrades))
                .collect(Collectors.toList());
        //.stream return a stream so after we have done what we need, we have to use .collect to convert it back to a list
        System.out.println("Task 2 - Students who got 50.0-100.0 sorted by grade:");
        list50to100SortedStudent.forEach(System.out::println);  // uncomment to run
        System.out.println();

        /*------*/

        /*Task 3*/
        Optional<Student> firstStudentIn50to100Range = listStudent.stream()
                .filter(aStudent -> (aStudent.getGrades() >= 50 && aStudent.getGrades() <= 100))
                .findFirst();
        //ifPresent take a consumer in which "Represents an operation that accepts a single input argument and returns no result."
        System.out.println("Task 3 - First Student who got 50.0-100.0:");
        firstStudentIn50to100Range.ifPresent(System.out::println);   // uncomment to run
        System.out.println();
        /*------*/

        /*Task 4*/
        Comparator<Student> compareObject = Student::compareTo;
        System.out.println("Task 4a - Students in ascending order by last name then first:");
        listStudent.stream().sorted(compareObject).forEach(System.out::println);             // uncomment to run
        System.out.println("Task 4b - Students in descending order by last name then first:");
        listStudent.stream().sorted(compareObject.reversed()).forEach(System.out::println);  // uncomment to run
        System.out.println();
        /*------*/

        /*Task 5*/
        //we have to use distinct after mapping which because distinct can't distinguish Student object
        Stream<String> uniqueLastNameList = listStudent.stream().map(Student::getLastName).distinct();
        System.out.println("Task 5 - Unique Student last names:");
        uniqueLastNameList.forEach(System.out::println);        // uncomment to run
        System.out.println();
        /*------*/

        /*Task 6*/
        //reuse compareObject from task 4
        Stream<String> sortedFullName = listStudent.stream().sorted(compareObject).map(Student::getName);
        System.out.println("Task 6 - Student names in order by last name then first name:");
        sortedFullName.forEach(System.out::println);                  // uncomment to run
        System.out.println();
        /*------*/

        /*Task 7*/
        Map<String, List<Student>> studentGrouped = listStudent.stream().collect(Collectors.groupingBy(Student::getDepartment));
        System.out.println("Task 7 - Students by department: ");
        studentGrouped.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(System.out::println);
        }); // uncomment to run
        System.out.println();
        /*------*/

        /*Task 8*/
        Map<String, Long> task8 = studentGrouped.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, anEntry -> Long.valueOf(anEntry.getValue().size())));
        System.out.println("Task 8 - Count of Students by department:\n");
        task8.forEach((key, value) -> {
            System.out.printf("%s has %s student(s)\n", key, value);
        });// uncomment to run
        System.out.println();

        /*------*/

        /*Task 9*/
        double totalScore = listStudent.stream().mapToDouble(Student::getGrades).reduce(0, Double::sum);
        System.out.printf("Task 9 - Sum of Students' grades: %s", totalScore); // uncomment to run
        System.out.println();
        /*------*/

        /*Task 10*/
        OptionalDouble avgScore = listStudent.stream().mapToDouble(Student::getGrades).average();
        System.out.print("Task 10 - Average of Students' grades: ");
        avgScore.ifPresent((a)->{
            System.out.printf("%.2f", a);
        });
        /*------*/

    }


}
