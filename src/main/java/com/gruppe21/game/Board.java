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

    /**
     * Returns the distance going clockwise around the board between two squares.
     * @param from
     * @param to
     * @return
     */
    public int getDistanceBetween(SquareController from, SquareController to) {
        int a = getIndexOfSquareController(from), b = getIndexOfSquareController(to);
        if (b < a) return getSquareControllers().length - a + b;
        else return b - a;
    }

    public SquareController getSquareControllerRelativeTo(SquareController squareController, int distance){
        return getSquareControllers()[(getIndexOfSquareController(squareController) + distance) % getSquareControllers().length];
    }


    public SquareController[] getSquareControllers() {
        return squareControllers;
    }

    public void setSquareControllers(SquareController[] squareControllers) {
        this.squareControllers = squareControllers;
    }

    /**
     *
     * @return
     */
    public SquareController getFirstSquareController() {
    return squareControllers[0];
    }

    private int getIndexOfSquareController(SquareController squareController){
        for (int i = 0; i < getSquareControllers().length; i++) {
            if (squareControllers[i] == squareController) return i;
        }
        return -1;
    }
}
