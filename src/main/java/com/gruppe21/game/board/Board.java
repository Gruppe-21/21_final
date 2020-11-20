package com.gruppe21.game.board;

import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.utils.BoardLoader;
import com.gruppe21.utils.arrayutils.OurArrayList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;


public class Board {
    private OurArrayList<Square> squares;

    public Board()  {
        try {
            squares = BoardLoader.loadBoard("main_board");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        for (Square square : squares.toArray(new Square[0])) {
            square.setBoard(this);
        }


    }
    public OurArrayList<Square> getSquares() {
        return squares;
    }

    public PropertySquare[] getSquareWithColor(Color color){
        OurArrayList<PropertySquare> colorSquares = new OurArrayList<>();
        for (Square square: getSquares().toArray(new Square[0])) {
            if (square.getClass() == PropertySquare.class && ((PropertySquare) square).getColor() == color)
                colorSquares.add((PropertySquare) square);
        }
        return colorSquares.toArray(new PropertySquare[0]);
    }

    public Square[] getSquareOfClass(Class squareClass){
        OurArrayList<Square> squaresOfClass = new OurArrayList<>();
        for (Square square: getSquares().toArray(new Square[0])) {
            if (square.getClass() == squareClass) squaresOfClass.add(square);
        }
        return squaresOfClass.toArray(new Square[0]);
    }


    public Square getSquareAtRelativePosition(Square square, int distance){
        return getSquareAtIndex( (getSquareIndex(square) + distance) % getSquares().size());
    }

    public Square getSquareAtIndex(int index) {
        Square square =  squares.get(index);
        System.out.println(square.getName());
        return square;
    }

    public int getSquareIndex(Square square) {
        return squares.indexOf(square);
    }

}
