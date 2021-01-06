package com.gruppe21.squares.models;

public class PropertySquare extends Square {
    private int houses;

    public PropertySquare(int statusEffect, int id) {
        super(statusEffect, id);
        houses = 0;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
    }
}
