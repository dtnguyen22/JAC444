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
        //listStudent.forEach(System.out::println);  // uncomment to run
        /*------*/

        /*Task 2*/
        List<Student> list50to100SortedStudent = listStudent.stream().filter(aStudent -> {
            return (aStudent.getGrades() >= 50 && aStudent.getGrades() <= 100);
        }).sorted(Comparator.comparing(Student::getGrades)).collect(Collectors.toList());
        //list50to100SortedStudent.forEach(System.out::println);  // uncomment to run
        /*------*/

        /*Task 3*/
        Optional<Student> firstStudentIn50to100Range = listStudent.stream().filter(aStudent -> {
            return (aStudent.getGrades() >= 50 && aStudent.getGrades() <= 100);
        }).findFirst();
        //ifPresent take a consumer in which "Represents an operation that accepts a single input argument and returns no result."
        //firstStudentIn50to100Range.ifPresent(System.out::println);   // uncomment to run
        /*------*/

        /*Task 4*/
        Comparator<Student> compareObject = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        };
        //listStudent.stream().sorted(compareObject).forEach(System.out::println);             // uncomment to run
        //listStudent.stream().sorted(compareObject.reversed()).forEach(System.out::println);  // uncomment to run
        /*------*/

        /*Task 5*/
        //we have to use distinct after mapping which because distinct can't distinguish Student object
        Stream<String> uniqueLastNameList = listStudent.stream().map(Student::getLastName).distinct();
        //uniqueLastNameList.forEach(System.out::println);        // uncomment to run
        /*------*/

        /*Task 6*/
        //reuse compareObject from task 4
        Stream<String> sortedFullName = listStudent.stream().sorted(compareObject).map(Student::getName);
        //sortedFullName.forEach(System.out::println);                  // uncomment to run
        /*------*/

        /*Task 7*/
        Map<String, List<Student>> studentGrouped = listStudent.stream().collect(Collectors.groupingBy(Student::getDepartment));
        //studentGrouped.entrySet().forEach(System.out::println); // uncomment to run
        /*------*/

        /*Task 8*/
        Map<String, Long> task8 = studentGrouped.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, anEntry-> Long.valueOf(anEntry.getValue().size())));
        //task8.entrySet().forEach(System.out::println);// uncomment to run
        /*------*/

        /*Task 9*/
        double totalScore = listStudent.stream().mapToDouble(Student::getGrades).reduce(0, Double::sum);
        //System.out.print(totalScore);// uncomment to run
        /*------*/

        /*Task 10*/
        OptionalDouble avgScore = listStudent.stream().mapToDouble(Student::getGrades).average();
        //avgScore.ifPresent(System.out::println);// uncomment to run
        /*------*/

    }


}
