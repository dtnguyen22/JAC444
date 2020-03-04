package Chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");

        //drawing the board
        GridPane gridLayout = new GridPane();
        Board aBoard = new Board();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Rectangle rect = new Rectangle(80, 80);
                if ((x + y) % 2 == 0) {
                    rect.setFill(Color.WHITE);
                }else{
                    rect.setFill(Color.GRAY);
                }
                //for drawing the board
                Square aSquare = new Square(x,y);
                GridPane.setConstraints(rect, x, y);
                GridPane.setConstraints(aSquare, x, y);
                // square has to be put after rect, overlay problem
                gridLayout.getChildren().addAll(rect, aSquare);
                //for mapping square->position
                //set event listener for all the position.
                aSquare.setOnMouseClicked(e-> System.out.println("123"));
                //store all the square to the board
                aBoard.getSquares().add(aSquare);

            }
        }
        VBox mainLayout = new VBox();
        VBox vBoxMenu = initializeMenu();
        mainLayout.getChildren().addAll(vBoxMenu, gridLayout);
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private VBox initializeMenu(){
        MenuBar mainMenu = new MenuBar();
        Menu firstMenu = new Menu("Board");
        MenuItem menuItem1 = new MenuItem("New board");
        menuItem1.setOnAction(e->{
            System.out.println("123");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Hello wtf");
            alert.showAndWait();

        });
        MenuItem menuItem2 = new MenuItem("Clear the ");
        firstMenu.getItems().addAll(menuItem1, menuItem2);
        mainMenu.getMenus().add(firstMenu);
        VBox vBoxMenu = new VBox(mainMenu);
        return vBoxMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
