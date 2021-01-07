package com.gruppe21.squares.models;

import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class CardSquare extends Square {

    public CardSquare(int id, String label, String description, Color color, int statusEffect) {
        super(id, label, description, color, statusEffect);
        GUI_Chance field = new GUI_Chance();
        setGuiField(field);
    }

    public CardSquare(Element xmlTag){
        this(   parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0);
    }
}