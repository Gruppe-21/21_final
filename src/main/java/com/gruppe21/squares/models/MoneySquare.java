package com.gruppe21.squares.models;

import gui_fields.GUI_Start;

public class MoneySquare extends Square {
    public MoneySquare(int statusEffect, int id) {
        super(statusEffect, id);
        setGuiField(new GUI_Start());
    }
}
