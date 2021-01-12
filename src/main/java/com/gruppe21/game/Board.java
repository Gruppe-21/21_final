package com.gruppe21.game;

import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.PropertySquare;
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
            setGroups();

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

    /**
     *
     * @param from
     * @param squareControllers
     * @return
     */
    public SquareController closestSquareController(SquareController from, SquareController[] squareControllers) {
        int indexCloset = 0, minDistance = getDistanceBetween(from, squareControllers[0]);
        for (int i = 1; i < squareControllers.length; i++) {
            int distance = getDistanceBetween(from, squareControllers[i]);
            if (distance < minDistance){
                minDistance = distance;
                indexCloset = i;
            }
        }
        return squareControllers[indexCloset];
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

    private PropertySquareController[] getSquaresOfColor(String color, int groupSize){
       int propertyCount = 0;

        for (SquareController squareController : squareControllers) {
            if( squareController.getClass() == PropertySquareController.class)
            {
                propertyCount++;
            }
        }
        PropertySquareController[] group = new PropertySquareController[groupSize];
        int index = 0;
        for (int i = 0; i < squareControllers.length; i++) {
            if(index > groupSize -1){
                break;
            }
            if( squareControllers[i].getClass() == PropertySquareController.class)
            {
                PropertySquareController pController = (PropertySquareController)squareControllers[i];
                if(pController.getGroupColor().equals(color)){
                  group[index] = pController;
                }
                index++;
            }
        }
        return group;
    }

    private void setGroups(){
        for (SquareController squareController : squareControllers) {
            if( squareController.getClass() == PropertySquareController.class)
            {
                PropertySquareController pController = (PropertySquareController)squareController;
                pController.setGroup(getSquaresOfColor(pController.getGroupColor(), 3));
            }
        }
    }
}
