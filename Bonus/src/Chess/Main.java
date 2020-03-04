package Chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");
        VBox vBoxMenu = initializeMenu();

        //drawing the board
        GridPane gridLayout = new GridPane();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Rectangle rect = new Rectangle(80, 80);
                if ((x + y) % 2 == 0) {
                    rect.setFill(Color.WHITE);
                }else{
                    rect.setFill(Color.GRAY);
                }
                GridPane.setConstraints(rect, x, y);
                gridLayout.getChildren().add(rect);
            }
        }
        VBox mainLayout = new VBox();
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
