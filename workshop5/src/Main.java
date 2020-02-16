import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application  {
    Stage window;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Student Management");
        window.setMinWidth(300);

        //show student label and button
        Label labelShow = new Label("Show student info");
        Button showStdButton = new Button("Show Student");
        TextField fileNameInput = new TextField();
        fileNameInput.setPromptText("File name");

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
        TextField stdFileName = new TextField();
        stdFileName.setPromptText("Save to");
        TextArea stdCoursesInput = new TextArea();
        stdCoursesInput.setMaxWidth(200);
        stdCoursesInput.setMaxHeight(80);

        showStdButton.setOnAction(e->{
            StudentReader.display(fileNameInput.getText());
            fileNameInput.setText("");
        });
        addStdButton.setOnAction(e->{
            StudentCreator.add(stdIdInput.getText(), stdFNameInput.getText(), stdLNameInput.getText(), stdCoursesInput.getText(), stdFileName.getText());
            stdIdInput.setText("");
            stdFNameInput.setText("");
            stdLNameInput.setText("");
            stdCoursesInput.setText("");
        });





        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        GridPane.setConstraints(labelShow, 0,0,1,1);
        GridPane.setConstraints(showStdButton, 0, 1);
        GridPane.setConstraints(fileNameInput,1,1);
        GridPane.setConstraints(labelAdd, 0 ,2 );
        GridPane.setConstraints(stdIdLbl, 0,3);
        GridPane.setConstraints(stdIdInput,1,3);
        GridPane.setConstraints(stdFNameLbl,0,4);
        GridPane.setConstraints(stdFNameInput, 1,4);
        GridPane.setConstraints(stdLNameLbl,0,5);
        GridPane.setConstraints(stdLNameInput, 1,5);
        GridPane.setConstraints(stdCoursesLbl,0,6);
        GridPane.setConstraints(stdCoursesInput, 1,6);
        GridPane.setConstraints(addStdButton, 0,7);
        GridPane.setConstraints(stdFileName, 1,7);



        grid.getChildren().setAll(labelShow, showStdButton,fileNameInput, labelAdd ,stdIdLbl,
                stdIdInput,stdFNameInput,stdFNameLbl,stdLNameInput,stdLNameLbl,stdCoursesInput,stdCoursesLbl,addStdButton,stdFileName);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();

    }
}
