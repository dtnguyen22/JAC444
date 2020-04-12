import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {
    Stage window, subWindow;
    DatabaseConnector dbc = new DatabaseConnector();


    @Override
    public void start(Stage primaryStage) throws Exception{
        dbc.initialize();
        window = primaryStage;
        window.setTitle("Student Management");

        //show student label and button
        Label labelShow = new Label("Show student from DB ");
        Button showStdButton = new Button("Show Student");

        //add student labels and buttons
        Label labelAdd = new Label("Add new student");
        Button addStdButton = new Button("Add Student");

        Label stdIdLbl = new Label("ID");
        Label stdFNameLbl = new Label("First Name");
        Label stdLNameLbl = new Label("Last Name");
        Label stdCoursesLbl = new Label("Courses");
        TextField stdIdInput = new TextField();
        TextField stdFNameInput = new TextField();
        TextField stdLNameInput = new TextField();
        Label labelAddStd = new Label("Add student to DB");
        TextField stdCoursesInput = new TextField();

        showStdButton.setOnAction(e->{
            List<Student> studentList;
            studentList = dbc.getAllStudent();
            TableView tableView = new TableView();
            TableColumn<String, Student> column1 = new TableColumn<>("Student ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("stdID"));
            TableColumn<String, Student> column2 = new TableColumn<>("First Name");
            column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            TableColumn<String, Student> column3 = new TableColumn<>("Last Name");
            column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            TableColumn<String, Student> column4 = new TableColumn<>("Course");
            column4.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            tableView.getColumns().addAll(column1,column2,column3,column4);
            studentList.forEach(tableView.getItems()::add);
            VBox layout = new VBox(tableView);
            Scene scene = new Scene(layout);
            subWindow = new Stage();
            subWindow.setScene(scene);
            subWindow.setTitle("Student Management");
            window.setResizable(false);
            subWindow.sizeToScene();
            subWindow.showAndWait();
        });
        addStdButton.setOnAction(e->{
            Student aStudent = new Student(Integer.parseInt(stdIdInput.getText()), stdFNameInput.getText(), stdLNameInput.getText(), stdCoursesInput.getText());
            boolean res = dbc.saveStudent(aStudent);
            if(res){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Student is added to DB");
                stdIdInput.setText("");
                stdFNameInput.setText("");
                stdLNameInput.setText("");
                stdCoursesInput.setText("");
                alert.showAndWait();
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        GridPane.setConstraints(labelShow, 0, 0);
        GridPane.setConstraints(showStdButton, 1, 0);
        GridPane.setConstraints(labelAdd, 0 ,2 );
        GridPane.setConstraints(stdIdLbl, 0,3);
        GridPane.setConstraints(stdIdInput,1,3);
        GridPane.setConstraints(stdFNameLbl,0,4);
        GridPane.setConstraints(stdFNameInput, 1,4);
        GridPane.setConstraints(stdLNameLbl,0,5);
        GridPane.setConstraints(stdLNameInput, 1,5);
        GridPane.setConstraints(stdCoursesLbl,0,6);
        GridPane.setConstraints(stdCoursesInput, 1,6);
        GridPane.setConstraints(labelAddStd, 0,7);
        GridPane.setConstraints(addStdButton, 1,7);

        grid.getChildren().setAll(labelShow, showStdButton, labelAdd ,stdIdLbl,
                stdIdInput,stdFNameInput,stdFNameLbl,stdLNameInput,stdLNameLbl,stdCoursesInput,stdCoursesLbl,addStdButton, labelAddStd);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.setResizable(false);
        window.sizeToScene();
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
