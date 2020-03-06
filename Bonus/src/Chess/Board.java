package Chess;


import javafx.event.Event;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    public GridPane initialize(){
        GridPane gridLayout = new GridPane();
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
                aSquare.setOnMouseClicked(e->{
                    this.aSquareIsClicked(aSquare);
                });
                //store all the square to the board
                this.getSquares().add(aSquare);
            }
        }
        return gridLayout;
    }

    //for event listener on all the squares on the board
    //if it there is no move in moveHistory, set aSquare with a piece
    public void aSquareIsClicked(Square aSquare){
        if(this.moveHistory == null){
            Knight aKnight = new Knight(aSquare);
            aSquare.setSquareWith(aKnight);
            //create move history and add the first move into it
            this.moveHistory = new Move(aKnight);
            this.moveHistory.getMoves().add(aSquare);
        }else{
            //if the clicked square is in the historyMove
            if(this.moveHistory.getMoves().contains(aSquare)){
                System.out.println("Illegal move!");
            }else{
                //moveHistory -> last Square-> piece inside
                Move possibleMove = this.moveHistory.getMoves().getLast().getPiece().getPossibleMoves(this);
            }
        }


    }



    public List<Square> getSquares() {
        return squares;
    }



    public Square getSquareByXAndY(int x, int y){
        List<Square> aSquare = this.getSquares().stream().filter(current-> (current.get_X()==x && current.get_Y()==y)).collect(Collectors.toList());
        return aSquare.get(0);
    }

    public boolean isOccupied(Square aSquare){
        if (aSquare.getPiece() != null){
            System.out.println(aSquare + " is occupied");
            return true;
        }else{
            return false;
        }
    }

}
