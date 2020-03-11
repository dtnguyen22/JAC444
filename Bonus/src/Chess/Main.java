package Chess;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;


public class Main extends Application {

    Stage window;
    VBox mainLayout;
    GridPane boardLayout;
    MenuBar mainMenu;
    Scene scene;
    Board aBoard;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Bonus workshop - 49 and 11");
        mainLayout = new VBox();
        //menu
        mainMenu = initializeMenu();
        //body
        initializeBody();
        //body-end
        mainLayout.getChildren().add(mainMenu);
        mainLayout.getChildren().add(boardLayout);
        scene = new Scene(mainLayout);
        window.setScene(scene);
        window.setResizable(false);
        window.sizeToScene();
        window.show();
    }

    public void initializeBody() {
        mainLayout.getChildren().removeIf(e -> e == boardLayout);
        boardLayout = new GridPane();
        boardLayout.setMinHeight(640);
        boardLayout.setMinWidth(640);
        Label lblWelcome = new Label("Welcome to bonus workshop");
        lblWelcome.setAlignment(Pos.CENTER);
        lblWelcome.setFont(Font.font(25));
        lblWelcome.setPrefSize(640, 640);
        boardLayout.add(lblWelcome, 0, 0);
    }

    public MenuBar initializeMenu() {
        MenuBar mainMenu = new MenuBar();
        mainMenu.setMinWidth(640);
        Menu firstMenu = new Menu("Board");
        //first menu
        MenuItem menuItem1 = new MenuItem("Manual move");
        menuItem1.setOnAction(event -> {
            mainLayout.getChildren().remove(boardLayout);
            aBoard = new Board();
            boardLayout = aBoard.initialize();
            mainLayout.getChildren().add(boardLayout);
            System.out.println(aBoard);
        });
        MenuItem menuItem2 = new MenuItem("Find knight tour");
        menuItem2.setOnAction(event -> {
            if(aBoard != null && aBoard.getMoveHistory() != null){
                aBoard.findKnightTour();
            }else{
                System.out.println("Please make a move manually first");
            }
        });
        firstMenu.getItems().addAll(menuItem1, menuItem2);
        //second menu
        Menu secondMenu = new Menu("Auto");
        //first menu
        MenuItem menu2Item1 = new MenuItem("Find 64 tours");
        menu2Item1.setOnAction(event -> {
            mainLayout.getChildren().remove(boardLayout);
            aBoard = new Board();
            boardLayout = aBoard.initialize();
            mainLayout.getChildren().add(boardLayout);
            aBoard.find64KnightTour();
        });
        secondMenu.getItems().addAll(menu2Item1);
        //add 2 to main menu
        mainMenu.getMenus().add(firstMenu);
        mainMenu.getMenus().add(secondMenu);
        return mainMenu;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
