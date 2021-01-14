package com.gruppe21.squares.models;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.utils.ColorUtil;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;



public class TeleportSquare extends Square {
    private int destinationId;
    private SquareController destinationController;

    public TeleportSquare(int id, String label, String description, Color color, int statusEffect, int destinationId) {
        super(id, label, description, color, statusEffect);
        this.destinationId = destinationId;

    }

    public TeleportSquare(Element xmlTag){
        this(   parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                parseInt(xmlTag.getAttribute("statusEffect")),
                parseInt(xmlTag.getAttribute("destinationId"))
        );
    }

    public int getDestinationId(){
        return destinationId;
    }

    public SquareController getDestinationController() {
        return destinationController;
    }

    public void setDestinationController(SquareController destinationController) {
        this.destinationController = destinationController;
    }

}
