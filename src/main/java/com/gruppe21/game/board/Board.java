package com.gruppe21.game.board;

import com.gruppe21.game.board.chancecard.*;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.utils.BoardLoader;
import com.gruppe21.utils.arrayutils.OurArrayList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private OurArrayList<Square> squares;
    private OurArrayList<ChanceCard> chanceCards;

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

    /**
     * Takes in consideration that one is an empty square that you cannot land on.
     *
     * @return square at the given integer. ex: index = 2 would in this game's case return square with name "Tower"
     */

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
