package com.gruppe21.squares.models;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Start;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;



public class TeleportSquare extends Square {
    private int destinationId;
    private SquareController destinationController;

    public TeleportSquare(int id, String label, String description, Color color, int statusEffect, int destinationId) {
        super(id, label, description, color, statusEffect);
        GUI_Empty field = new GUI_Empty();
        setGuiField(field);
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
