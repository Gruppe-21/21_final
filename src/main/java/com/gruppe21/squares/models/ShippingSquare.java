package com.gruppe21.squares.models;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Shipping;
import gui_fields.GUI_Street;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class ShippingSquare extends OwnableSquare {
    private OwnableSquareController[] group;

    public ShippingSquare(int id, String label, String description, Color color, int statusEffect, int price, String group, int... rent) {
        super(id, label, description, color, statusEffect, price, group);
        this.rent = rent;
        GUI_Shipping guiShipping = new GUI_Shipping();
        setGuiField(guiShipping);
    }

    public ShippingSquare(Element xmlTag){
        this(   parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0, // StatusEffect
                parseInt(xmlTag.getAttribute("price")),  // price
                xmlTag.getAttribute("color")
                //TODO: read rent array
        );
    }

    public GUI_Shipping getGuiField() {
        return (GUI_Shipping) (super.getGuiField());
    }

    public void setGuiField(GUI_Shipping guiField) {
        super.setGuiField(guiField);
    }

    public int getRent(){
        if (owner == null) return 0; //This should never happen
        int numOwnedInGroup = 0;
        for (OwnableSquareController ownableSquareController : getGroup()) {
            if (ownableSquareController.getOwner() == this.getOwner()) numOwnedInGroup++;
        }
        return rent[numOwnedInGroup];
    }

}
