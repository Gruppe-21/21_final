package com.gruppe21.squares.models;

import gui_fields.GUI_Chance;

public class CardSquare extends Square {
    public CardSquare(int statusEffect, int id) {
        super(statusEffect, id);
        GUI_Chance chance = new GUI_Chance();
        setGuiField(new GUI_Chance());
    }
}
