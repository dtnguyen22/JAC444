import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    public static final String USER_NAME =  "ws11";
    public static final String PASS_WORD =  "ws11";
    public static final String CONNECTION_STRING = "jdbc:mysql://192.168.159.136:3306/workshop11";
    public static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";

    public Connection connect()   {
        Connection conn;
        try{
            Class.forName(DRIVER_CLASS_MYSQL);
            //create a connection to the DB
            conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASS_WORD);
            if(conn != null){
                return conn;//return conn object if it is valid
            }
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e);
        }
        return null;
    }

    //initialize the db
    public void initialize(){
        Connection conn = this.connect();
        try{
            Statement stm = conn.createStatement();
            stm.execute("DROP TABLE if exists StudentCourse");
            stm.execute("DROP TABLE if exists Student");
            stm.execute("DROP TABLE if exists Course");
            stm.execute("CREATE TABLE Student(stdID int primary key, firstName varchar(30), lastName varchar(30))");
            stm.execute("CREATE TABLE Course(courseID varchar(30) primary key, courseName varchar(30))");
            stm.execute("CREATE TABLE StudentCourse(stdID int, courseID varchar(30))");
            stm.executeUpdate("INSERT INTO STUDENT VALUES (100, 'Mike', 'Shroud')");
            stm.executeUpdate("INSERT INTO STUDENT VALUES (200, 'Alex', 'Johnson')");
            stm.executeUpdate("INSERT INTO Course VALUES ('IPC144', 'Introduction to C')");
            stm.executeUpdate("INSERT INTO Course VALUES ('JAC444', 'Java for C++ programmer')");
            stm.executeUpdate("INSERT INTO StudentCourse VALUES (100, 'IPC144')");
            stm.executeUpdate("INSERT INTO StudentCourse VALUES (200, 'IPC144')");
            stm.executeUpdate("INSERT INTO StudentCourse VALUES (200, 'JAC444')");
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //
    public List<Student> getAllStudent()   {
        List<Student> studentList = new ArrayList<>();
        Connection conn = this.connect();
        try{
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * from STUDENT");
            while(resultSet.next()){
                Student aStd = new Student();
                aStd.setStdID(resultSet.getInt("stdID"));
                aStd.setFirstName(resultSet.getString("firstName"));
                aStd.setLastName(resultSet.getString("lastName"));
                studentList.add(aStd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return studentList;
    }

}
