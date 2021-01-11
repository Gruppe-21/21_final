package com.gruppe21.Board;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.*;
import com.gruppe21.utils.BoardLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class Board {



    SquareController[] square;


    public SquareController[] getSquare() {
        return square;
    }

    public Board(SquareController[] square){

        try {
            square = BoardLoader.loadBoard("main_board");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }
}
    public Square getSquareAtIndex(int index) {
        Square square = squares(index);
        return square;
    }

    //public CardSquare(int id, String label, String description, Color color, int statusEffect)

/*
this.square = square;

        square[0] = new PropertySquare(1 , "", "Første felt", Color.white, 0, 2000 );
        square[1] = new PropertySquare(2, "", "Andet felt", Color.yellow, 0, 3000);
        square[2] = new CardSquare(3, "", "Tredje felt",, 0);
        square[3] = new TeleportSquare(4, "", "Fjerde felt", Color.green, 0);
        square[4] = new MoneySquare(5, "", "Femte felt", , 0)
 */