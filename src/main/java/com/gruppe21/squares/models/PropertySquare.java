package com.gruppe21.squares.models;

import com.gruppe21.player.PlayerController;
import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class PropertySquare extends Square {
    private int houses;
    private int price;
    private PlayerController owner; //owner = null -> owner is the bank.

    public PropertySquare(int id, String label, String description, Color color, int statusEffect, int price) {
        super(id, label, description, color, statusEffect);
        this.price = price;
        houses = 0;
        owner = null;
        GUI_Street street = new GUI_Street();
        setGuiField(street);
    }

    public PropertySquare(Element xmlTag){
        this(   parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0, // StatusEffect
                parseInt(xmlTag.getAttribute("price"))); // price
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PlayerController getOwner(){
        return owner;
    }

    public void setOwner(PlayerController owner){
        this.owner = owner;
    }
}
