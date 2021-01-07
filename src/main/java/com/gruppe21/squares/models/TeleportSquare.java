package com.gruppe21.squares.models;

import gui_fields.GUI_Empty;
import gui_fields.GUI_Jail;

public class TeleportSquare extends Square {
    public TeleportSquare(int statusEffect, int id) {
        super(statusEffect, id);
        setGuiField(new GUI_Empty());
    }
}
