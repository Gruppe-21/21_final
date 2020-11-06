package com.gruppe21.game.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Square> squares;


    Board() {
        squares = new ArrayList<Square>();
        squares.add(new Square("Tower", "You have reached the tower!", +250, SquareType.Normal));
        squares.add(new Square("Crater", "You have found an enormous crater!", -100, SquareType.Normal));
        squares.add(new Square("Palace gates", "You see large palace gates in front of you!", +100, SquareType.Normal));
        squares.add(new Square("Cold Desert", "You are freezing in the cold desert!", -20, SquareType.Normal));
        squares.add(new Square("Walled city", "You have entered the great walled city!", +180, SquareType.Normal));
        squares.add(new Square("Monastery", "You found a monastery!", 0, SquareType.Normal));
        squares.add(new Square("Black cave", "You got lost in a black cave!", -70, SquareType.Normal));
        squares.add(new Square("Huts in the mountain", "You found some pretty huts in the mountain!", +60, SquareType.Normal));
        squares.add(new Square("The Werewall", "You have reached... THE WEREWALL. Lose some cash and get an extra turn!", -80, SquareType.ExtraTurn));
        squares.add(new Square("The pit", "You have reached the pit!", -50, SquareType.Normal));
        squares.add(new Square("Goldmine", "You found the mythical goldmine. It was filled with gold!", +650, SquareType.Normal));

        for (Square square : squares) {
            square.setEventText(square.getEventText()
                    + " You " + (square.getModifyValue() < 0 ? "lose" : "gain") + " ¤" + square.getModifyValue());
        }
    }

    public List<Square> getSquares() {
        return squares;
    }

    /**
     * Takes in consideration that one is an empty square that you cannot land on.
     *
     * @return square at the given integer. ex: num = 2 would in this game's case return square with name "Tower"
     */
    public Square getSquareAtNumber(int num) {
        return num <= 1 ? null : squares.get(num - 2);
    }

    public int getSquareIndex(Square square) {
        return squares.indexOf(square);
    }


}
