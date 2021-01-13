package com.gruppe21.game;

import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.controllers.TeleportSquareController;
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
            for (SquareController square: squareControllers) {
                if (square instanceof TeleportSquareController){
                    ((TeleportSquareController) square).setDestinationController(getSquareControllerFromId(square.));
                }
            }

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
    
    private OwnableSquareController[] getSquaresOfColor(String color){
        int colorCount = 0;
        for (SquareController squareController : squareControllers) {
            if (squareController instanceof PropertySquareController || squareController instanceof OwnableSquareController){
                if (((OwnableSquareController)squareController).getGroupColor().equals(color)){
                    colorCount++;
                }
            }
        }
        OwnableSquareController[] group = new OwnableSquareController[colorCount];
        for (int i = 0; i < squareControllers.length ; i++) {
            if (squareControllers[i] instanceof PropertySquareController || squareControllers[i] instanceof OwnableSquareController){
                if (((OwnableSquareController)squareControllers[i]).getGroupColor().equals(color)){
                    group[--colorCount] = (OwnableSquareController)squareControllers[i];
                }
            }
        }
        return group;
    }
/*
    private OwnableSquareController[] getSquaresOfColor(String color){
       int propertyCount = 0;

        for (SquareController squareController : squareControllers) {
            if( squareController.getClass() == OwnableSquareController.class)
            {
                propertyCount++;
            }
        }
        OwnableSquareController[] group = new OwnableSquareController[10];
        int index = 0;
        for (int i = 0; i < squareControllers.length; i++) {
            if(index > propertyCount - 1){
                break;
            }
            if( squareControllers[i].getClass() == OwnableSquareController.class)
            {
                OwnableSquareController pController = (OwnableSquareController)squareControllers[i];
              //  System.out.println("Color1: " + pController.getGroupColor() + " Color2: " + color);
                if(pController.getGroupColor().equals(color)){
                  group[index] = pController;
                 index++;
                }
            }
        }

        int actualSize = 0;
        for (OwnableSquareController ownableSquareController : group) {
            if (ownableSquareController == null) {
                break;
            }
            actualSize++;
        }
        index = 0;
        if(group.length != actualSize){
            OwnableSquareController[] newGroup = new OwnableSquareController[actualSize];
            //System.out.println("Actual Size: " +  actualSize);

            for (OwnableSquareController ownableSquareController : group) {
                if (ownableSquareController != null && index <= actualSize - 1) {
                    newGroup[index] = ownableSquareController;
                    index++;
                }
            }
            return newGroup;
        }
        return group;
    }
*/
    private void setGroups(){
        for (SquareController squareController : squareControllers) {
            if( squareController instanceof OwnableSquareController)
            {
                OwnableSquareController pController = (OwnableSquareController)squareController;
                if (pController.getGroup() != null) continue;
                OwnableSquareController[] group = getSquaresOfColor(pController.getGroupColor());
                for (OwnableSquareController groupController: group) {
                    groupController.setGroup(group);
                }
            }
        }
    }
}
