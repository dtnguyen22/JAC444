package Chess;


import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final int maxVertical = 8;
    private final int maxHorizontal = 8;
    private List<Square> squares;
    private Move moveHistory;

    public Board() {
        this.squares = new ArrayList<>();
    }

    public VBox initialize() {
        GridPane gridLayout = new GridPane();
        MenuBar mainMenu = new MenuBar();
        VBox mainLayout = new VBox();
        /*menu bar ----------------------------------*/
        //menu 1
        Menu firstMenu = new Menu("Board");
        MenuItem menuItem1 = new MenuItem("New board");
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
        /*--------------------------------------------menu bar*/

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Rectangle rect = new Rectangle(80, 80);
                if ((x + y) % 2 == 0) {
                    rect.setFill(Color.WHITE);
                } else {
                    rect.setFill(Color.GRAY);
                }
                //for drawing the board
                Square aSquare = new Square(x, y);
                GridPane.setConstraints(rect, x, y);
                GridPane.setConstraints(aSquare, x, y);
                // square has to be put after rect, overlay problem
                gridLayout.getChildren().addAll(rect, aSquare);
                //for mapping square->position
                //set event listener for all the position.
                aSquare.setOnMouseClicked(e -> {
                    this.aSquareIsClicked(aSquare);
                });
                //store all the square to the board
                this.getSquares().add(aSquare);
            }
        }
        mainLayout.getChildren().addAll(mainMenu, gridLayout);
        return mainLayout;
    }

    //for event listener on all the squares on the board
    //if it there is no move in moveHistory, set aSquare with a piece
    public void aSquareIsClicked(Square clickedSquare) {
        if (this.moveHistory == null) {
            System.out.println("move history == null!");

            Knight aKnight = new Knight(clickedSquare);
            clickedSquare.setSquareWith(aKnight);
            //calculate and set possible move for the piece inside that square
            clickedSquare.getPiece().calculatePossibleMoves(this);
            //create move history and add the first move into it
            this.moveHistory = new Move(aKnight);
            this.moveHistory.getMoves().add(clickedSquare);
        } else {
            //get allowed moves based on last move
            Move allowedMoveBasedOnLastMove = this.moveHistory.getMoves().getLast().getPiece().getPossibleMove();
            //next move is not in allowed move
            if(clickedSquare == this.moveHistory.getMoves().getLast()){
                System.out.println("Illegal move - do not make a move on previous position");
                return;
            }
            if (!allowedMoveBasedOnLastMove.getMoves().contains(clickedSquare)) {
                System.out.println("Illegal move for a knight");
            }else {
                // allowedMoveBaseOnLastMove - allTheMovesWereMade = next allowed move
                allowedMoveBasedOnLastMove.getMoves().removeAll(this.moveHistory.getMoves());
                allowedMoveBasedOnLastMove.getMoves().forEach((System.out::println));
                //set new square with previous square's piece
                clickedSquare.setSquareWith(this.moveHistory.getPiece());
                //clear the image before setting new text
                this.moveHistory.getMoves().getLast().setGraphic(null);
                //get the index of last square
                int aNumber = this.moveHistory.getMoves().indexOf(this.moveHistory.getMoves().getLast());
                //set previous square text
                this.moveHistory.getMoves().getLast().setText(Integer.toString(aNumber));
                //add the square which is newly set to the moveHistory
                this.moveHistory.getMoves().add(clickedSquare);
                //update position for newly set piece
                this.moveHistory.getMoves().getLast().getPiece().setPosition(clickedSquare);
                //calculate and set possible move for the piece inside new square, prepare for next move
                this.moveHistory.getMoves().getLast().getPiece().calculatePossibleMoves(this);
                this.moveHistory.getMoves().getLast().getPiece().getPossibleMove().getMoves().forEach(System.out::println);
            }
        }
        //after everything is done add the square to history move
    }


    public List<Square> getSquares() {
        return squares;
    }

    public Square getSquareByXAndY(int x, int y) {
        List<Square> aSquare = this.getSquares().stream().filter(current -> (current.getX() == x && current.getY() == y)).collect(Collectors.toList());
        return aSquare.get(0);
    }

    public boolean isOccupied(Square aSquare) {
        return aSquare.getPiece() != null;
    }

}
