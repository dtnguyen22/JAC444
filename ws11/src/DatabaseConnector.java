import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    public static final String USER_NAME = "ws11";
    public static final String PASS_WORD = "ws11";
    public static final String CONNECTION_STRING = "jdbc:mysql://192.168.159.136:3306/workshop11";
    public static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";

    public Connection connect() {
        Connection conn;
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            //create a connection to the DB
            conn = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASS_WORD);
            if (conn != null) {
                return conn;//return conn object if it is valid
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    //initialize the db
    public void initialize() {
        Connection conn = this.connect();
        Statement stm = null;
        if (conn == null) {
            System.out.println("Database Connector failed to connect to the db");
            return;
        }
        try {
            stm = conn.createStatement();
            stm.execute("DROP TABLE if exists Student");
            stm.execute("CREATE TABLE Student(stdID int primary key, firstName varchar(30), lastName varchar(30), courseName varchar(30))");
            stm.executeUpdate("INSERT INTO STUDENT VALUES (100, 'Mike', 'Shroud', 'IPC144')");
            stm.executeUpdate("INSERT INTO STUDENT VALUES (200, 'Alex', 'Johnson', 'APC100')");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stm != null){
                    stm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //
    public List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        Connection conn = this.connect();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * from STUDENT");
            while (resultSet.next()) {
                Student aStd = new Student();
                aStd.setStdID(resultSet.getInt("stdID"));
                aStd.setFirstName(resultSet.getString("firstName"));
                aStd.setLastName(resultSet.getString("lastName"));
                aStd.setCourseName(resultSet.getString("courseName"));
                studentList.add(aStd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(stm != null){
                    stm.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentList;
    }

    public boolean saveStudent(Student aStudent)   {
        Connection conn = this.connect();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = conn.prepareStatement("INSERT INTO STUDENT (`stdID`, `firstName`,`lastName`,`courseName`) VALUES (?, ?, ? ,?)");
            preparedStatement.setInt(1, aStudent.getStdID());
            preparedStatement.setString(2, aStudent.getFirstName());
            preparedStatement.setString(3, aStudent.getLastName());
            preparedStatement.setString(4, aStudent.getCourseName());
            int res = preparedStatement.executeUpdate();
            if(res > 0){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement != null){
                    preparedStatement.close();
                }
                if(conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
