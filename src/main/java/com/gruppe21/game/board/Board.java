package com.gruppe21.game.board;

import com.gruppe21.game.board.chancecard.*;

import java.util.ArrayList;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.utils.BoardLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Board {
    private List<Square> squares;
    private List<ChanceCard> chanceCards;

    public Board() throws ParserConfigurationException, IOException, SAXException {

        squares = BoardLoader.loadBoard("main_board");

        chanceCards = new ArrayList<ChanceCard>();
        chanceCards.add(new ChanceCardGetOutOfJailFree("You can get out of jail for free if needed."));
        chanceCards.add(new ChanceCardMoney("You have made your homework, receive 2#.",+2,false,true,false));
        chanceCards.add(new ChanceCardMoney("You have eaten to much candy. Pay 2# to the bank.",-2,true,false,false));
        chanceCards.add(new ChanceCardMoney("It's your birthday! Everyone gives you 1#",+1,false,false,false));
        chanceCards.add(new ChanceCardStart("Move to the start area. Receive 2#",+2));
        chanceCards.add(new ChanceCardMove("Move to Strandpromenaden.", 23,false,false,false,false));
        chanceCards.add(new ChanceCardMove("Move UP TO 5 squares forward.",0,false,false,true,false));
        chanceCards.add(new ChanceCardMove("Move 1 square forward, OR take another chance card",0,true,false,false,false));
    }

    public Board(String FileName) throws ParserConfigurationException, IOException, SAXException {
        squares = BoardLoader.loadBoard(FileName);

        chanceCards = new ArrayList<ChanceCard>();
        chanceCards.add(new ChanceCardGetOutOfJailFree("You can get out of jail for free if needed."));
        chanceCards.add(new ChanceCardMoney("You have made your homework, receive 2#.",+2,false,true,false));
        chanceCards.add(new ChanceCardMoney("You have eaten to much candy. Pay 2# to the bank.",-2,true,false,false));
        chanceCards.add(new ChanceCardMoney("It's your birthday! Everyone gives you 1#",+1,false,false,false));
        chanceCards.add(new ChanceCardStart("Move to the start area. Receive 2#",+2));
        chanceCards.add(new ChanceCardMove("Move to Strandpromenaden.", 23,false,false,false,false));
        chanceCards.add(new ChanceCardMove("Move UP TO 5 squares forward.",0,false,false,true,false));
        chanceCards.add(new ChanceCardMove("Move 1 square forward, OR take another chance card",0,true,false,false,false));
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
