package Chess;

import javafx.application.Application;
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
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends Application {
    private GridPane boardGridLayout;
    private Board aBoard;
    private MenuBar mainMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");

        VBox mainLayout = new VBox();
        VBox vBoxMenu = initializeMenu();

        //drawing the board
        aBoard = new Board();
        boardGridLayout = aBoard.initialize();
        //
        mainLayout.getChildren().addAll(vBoxMenu, boardGridLayout);
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void squareClicked(Square aSquare) {
//        try(FileInputStream fis = new FileInputStream(Piece.KNIGHT_PIC_URL)) {
//            Image img = new Image(fis);
//            gridLayout.add(new ImageView(img), aSquare.get_X() ,aSquare.get_Y());
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

    private VBox initializeMenu() {
        mainMenu = new MenuBar();
        //menu 1
        Menu firstMenu = new Menu("Board");
        MenuItem menuItem1 = new MenuItem("New board");
        menuItem1.setOnAction(e -> {

        });
        MenuItem menuItem2 = new MenuItem("Clear the ");
        firstMenu.getItems().addAll(menuItem1, menuItem2);
        //menu 2
        Menu secondMenu = new Menu("Select Piece");
        RadioMenuItem radKnight = new RadioMenuItem("Knight");
        RadioMenuItem radRook = new RadioMenuItem("Rook");
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(radKnight);
        toggleGroup.getToggles().add(radRook);
        secondMenu.getItems().add(radKnight);
        secondMenu.getItems().add(radRook);

        mainMenu.getMenus().add(firstMenu);
        mainMenu.getMenus().add(secondMenu);

        return new VBox(mainMenu);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
