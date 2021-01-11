package com.gruppe21.game;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.Square;
import com.gruppe21.utils.BoardLoader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Board {

    private SquareController[] squareControllers;

    public Board(){
        try {
            squareControllers = BoardLoader.loadBoard("main_board");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            squareControllers = new SquareController[0];
        }
    }

    public SquareController getSquareControllerFromId(int id) {
        for (SquareController squareController : squareControllers) {
            if(squareController.getId() == id)
                return squareController;
        }
        return null;
    }

    public SquareController closestSquareController(SquareController from, SquareController[] SquareControllers) {
        return null;
    }

    public int getDistanceBetween(SquareController a, SquareController b) {
// USE Math.abs(b - a)
        return 0;
    }

    public SquareController getSquareControllerRelativeTo(SquareController squareController, int distance){
        return null;
    }


    public SquareController[] getSquareControllers() {
        return squareControllers;
    }

    public void setSquareControllers(SquareController[] squareControllers) {
        this.squareControllers = squareControllers;
    }

    public SquareController getFirstSquareController() {
    return squareControllers[0];
    }
}
