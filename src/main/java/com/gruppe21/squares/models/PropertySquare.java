package com.gruppe21.squares.models;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

public class PropertySquare extends Square {
    private int houses;

    public PropertySquare(int statusEffect, int id) {
        super(statusEffect, id);
        houses = 0;
        GUI_Street street = new GUI_Street();
        setGuiField(street);
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }
}
