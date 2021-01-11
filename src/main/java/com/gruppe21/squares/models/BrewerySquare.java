package com.gruppe21.squares.models;

import com.gruppe21.game.GameController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Brewery;
import gui_fields.GUI_Shipping;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class BrewerySquare extends OwnableSquare {
    public BrewerySquare(int id, String label, String description, Color color, int statusEffect, int price) {
        super(id, label, description, color, statusEffect, price);

        GUI_Brewery guiBrewery = new GUI_Brewery();
        setGuiField(guiBrewery);
    }

    public BrewerySquare(Element xmlTag){
        this(   parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0, // StatusEffect
                parseInt(xmlTag.getAttribute("price"))  // price
                //TODO: read rent array
        );
    }

    public GUI_Brewery getGuiField() {
        return (GUI_Brewery) (super.getGuiField());
    }

    public void setGuiField(GUI_Brewery guiField) {
        super.setGuiField(guiField);
    }

    public int getRent(){
        if (owner == null) return 0; //This should never happen
        boolean doubleRent = true;
        for (OwnableSquareController ownableSquareController: getGroup()) {
            if (ownableSquareController.getOwner() != this.getOwner()){
                doubleRent = false;
                break;
            }
        }
        //TODO: This value should not be hardcoded and also rent should be in OwnableSquare.
        int rent = (GameController.getInstance().getCurrentPlayer().getLastRollForBrewery() * 100);
        return doubleRent? rent * 2 : rent;
    }

}
