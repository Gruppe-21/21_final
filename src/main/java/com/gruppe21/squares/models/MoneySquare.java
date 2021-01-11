package com.gruppe21.squares.models;

import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class MoneySquare extends Square {

    public MoneySquare(int id, String label, String description, Color color, int statusEffect) {
        super(id, label, description, color, statusEffect);
        GUI_Field field = new GUI_Start();
        setGuiField(field);
    }

    public MoneySquare(Element xmlTag){
        this(!xmlTag.getAttribute("id").equals("") ? parseInt(xmlTag.getAttribute("id")) : 0, //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0);
    }
}
