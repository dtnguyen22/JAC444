package Chess;


import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final int maxVertical = 8;
    private final int maxHorizontal = 8;
    private List<Square> squares;
    private Move moveHistory;

    public Board() {
        this.squares = new ArrayList<>();
    }

    public GridPane initialize() {
        GridPane gridLayout = new GridPane();
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
        return gridLayout;
    }

    //for event listener on all the squares on the board
    //if it there is no move in moveHistory, set aSquare with a piece
    public void aSquareIsClicked(Square clickedSquare) {
        if (this.moveHistory == null) {
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
            if (clickedSquare == this.moveHistory.getMoves().getLast()) {
                System.out.println("Illegal move - do not make a move on previous position");
                return;
            }
            //next move has to be in the list of possibleMoves which is calculated above
            if (!allowedMoveBasedOnLastMove.getMoves().contains(clickedSquare)) {
                System.out.println("Illegal move for a knight");
            } else {
                // and next move has to be an unvisited square
                // allowedMoveBaseOnLastMove - allTheMovesWereMade = next allowed move
                allowedMoveBasedOnLastMove.getMoves().removeAll(this.moveHistory.getMoves());
//                allowedMoveBasedOnLastMove.getMoves().forEach((System.out::println));//debug purpose
                //set possible move square to BLUE
                this.moveHistory.getPiece().getPossibleMove().getMoves().forEach(sq -> {
                    sq.setBackground(Background.EMPTY);
                });
                //set new square with previous square's piece
                clickedSquare.setSquareWith(this.moveHistory.getPiece());
                //clear the image before setting new text
                this.moveHistory.getMoves().getLast().setGraphic(null);
                //get the index of last square
                int aNumber = this.moveHistory.getMoves().indexOf(this.moveHistory.getMoves().getLast()) + 1;
                //set previous square text
                this.moveHistory.getMoves().getLast().setText(Integer.toString(aNumber));
                //add the clickedSquare to the moveHistory
                this.moveHistory.getMoves().add(clickedSquare);
                //update position for newly set piece
                this.moveHistory.getMoves().getLast().getPiece().setPosition(clickedSquare);
                //calculate and set possibleMoves for the piece inside clickedSquare, prepare for next move
                this.moveHistory.getMoves().getLast().getPiece().calculatePossibleMoves(this);
                //printing out, debug purpose
//                this.moveHistory.getMoves().getLast().getPiece().getPossibleMove().getMoves().forEach(System.out::println);
            }
        }
        //after aSquare is processed, there only two possibilities
        //either clicked square becomes occupied or still !occupied
        //if it is occupied, that means the process above goes inside ELSE statement and a square is set with a piece.
        if (clickedSquare.isOccupied()) {
            clickedSquare.getPiece().getPossibleMove().getMoves().forEach(sq -> {
                sq.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            });
        }
        //        coming to this point, move history should have at least 1 piece
        //        if that piece doesn't have any moves, it means knight tour ends here.
    }

    public void popupMessage() {
        Alert finishAlert = new Alert(Alert.AlertType.INFORMATION);
        finishAlert.setTitle("Knight tour ends!");
        finishAlert.setContentText("Knight tour ends with " + this.moveHistory.getMoves().size() + " moves");
        finishAlert.setHeaderText(null);
        finishAlert.showAndWait();
    }

    public void find64KnightTour() {
        int count = 0;
        for (Square sq : this.getSquares()) {
            //clean all the squares
            this.getSquares().forEach(aSquare -> {
                aSquare.setPiece(null);
                aSquare.setText(null);
            });
            this.moveHistory = null;
            this.aSquareIsClicked(sq);
            if (this.findKnightTour()) {
                count++;
            }
        }
        System.out.printf("We have %s Knight FULL tour", count);
    }

    public boolean findKnightTour() {
        List<Square> possibleMoves;
        Square highestPrioritySquare;
        int[][] squarePriority = {{2, 3, 4, 4, 4, 4, 3, 2},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {4, 6, 8, 8, 8, 8, 6, 4},
                {3, 4, 6, 6, 6, 6, 4, 3},
                {2, 3, 4, 4, 4, 4, 3, 2}};

        Comparator<Square> squareComparator = new Comparator<Square>() {
            @Override
            public int compare(Square square, Square t1) {
                if (squarePriority[square.getX()][square.getY()] == squarePriority[t1.getX()][t1.getY()]) {
                    return 0;
                } else {
                    return squarePriority[square.getX()][square.getY()] > squarePriority[t1.getX()][t1.getY()] ? 1 : -1;
                }
            }
        };
        while (!this.getMoveHistory().getMoves().getLast().getPiece().getPossibleMove().getMoves().isEmpty()) {
            //get possible move for the clicked Square
            possibleMoves = this.getMoveHistory().getMoves().getLast().getPiece().getPossibleMove().getMoves();
            //find the one that has highest priority
            highestPrioritySquare = Collections.min(possibleMoves, squareComparator);
            Set<Square> duplicateRateSquareList = new HashSet<>();
            for (Square aSquare : possibleMoves) {
                if (squarePriority[aSquare.getX()][aSquare.getY()] == squarePriority[highestPrioritySquare.getX()][highestPrioritySquare.getY()]) {
                    duplicateRateSquareList.add(aSquare);
                }
            }
            //if there are several possible moves with the same rate, the one in outside layer has higher priority
            for (Square aSquare : duplicateRateSquareList) {
                if (aSquare.getX() < 3 && highestPrioritySquare.getX() < 3) {
                    if (aSquare.getX() < highestPrioritySquare.getX()) {
                        highestPrioritySquare = aSquare;
                    }
                } else if (aSquare.getX() > 3 && highestPrioritySquare.getX() > 3) {
                    if (aSquare.getX() > highestPrioritySquare.getX()) {
                        highestPrioritySquare = aSquare;
                    }
                }
                if (aSquare.getY() < 3 && highestPrioritySquare.getY() < 3) {
                    if (aSquare.getY() < highestPrioritySquare.getY()) {
                        highestPrioritySquare = aSquare;
                    }
                } else if (aSquare.getY() > 3 && highestPrioritySquare.getY() > 3) {
                    if (aSquare.getY() > highestPrioritySquare.getY()) {
                        highestPrioritySquare = aSquare;
                    }
                }
            }
            if (highestPrioritySquare != null) {
                this.aSquareIsClicked(highestPrioritySquare);
                updateSquarePriority(squarePriority, possibleMoves);
            }
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                System.out.printf("%2s ", this.moveHistory.getMoves().indexOf(this.getSquareByXAndY(x, y)));
            }
            System.out.print('\n');
        }
        if (this.getMoveHistory().getMoves().size() == 64) {
            System.out.println("This is a full tour start at " + this.moveHistory.getMoves().getFirst());
            System.out.print('\n');
            return true;
        } else {
            System.out.print('\n');
            return false;
        }

    }

    public void updateSquarePriority(int[][] squarePriorityArray, List<Square> nextPossibleMoveList) {
        for (Square aSquare : nextPossibleMoveList) {
            squarePriorityArray[aSquare.getX()][aSquare.getY()]--;
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    public Move getMoveHistory() {
        return moveHistory;
    }

    public Square getSquareByXAndY(int x, int y) {
        List<Square> aSquare = this.getSquares().stream().filter(current -> (current.getX() == x && current.getY() == y)).collect(Collectors.toList());
        return aSquare.get(0);
    }

    public boolean isOccupied(Square aSquare) {
        return aSquare.getPiece() != null;
    }

}
