package com.gruppe21.game.board;

import com.gruppe21.game.board.squares.Square;
import com.gruppe21.utils.BoardLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Board {
    private List<Square> squares;


    public Board() throws ParserConfigurationException, IOException, SAXException {

        squares = BoardLoader.loadBoard("main_board");
    }

    public Board(String FileName) throws ParserConfigurationException, IOException, SAXException {

        squares = BoardLoader.loadBoard(FileName);
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
