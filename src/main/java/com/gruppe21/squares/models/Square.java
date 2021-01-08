package com.gruppe21.squares.models;

import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Field;
import gui_fields.GUI_Start;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class Square {
    /**
     * The effect that this square should apply to player when landed on
     */
    private int statusEffect;

    /**
     * Identifier for the square.
     * Use this for referencing on the board.
     */
    private int id;

    /**
     * Identifier used for getting the localized name of the square
     */
    private String nameLocalisationId;

    /**
     * Identifier used for getting the localized description of the square
     */
    private String descriptionLocalisationId;

    /**
     * The visible field for the gui
     */
    private GUI_Field guiField;

    /**
     * The background color
     */
    private Color color;

    public Square(int id, String nameLocalisationId, String descriptionLocalisationId, Color color, int statusEffect) {
        this.guiField = new GUI_Start();
        this.id = id;
        this.nameLocalisationId = nameLocalisationId;
        this.descriptionLocalisationId = descriptionLocalisationId;
        this.color = color;
        this.statusEffect = statusEffect;
    }

    public Square(Element xmlTag) {
        this(parseInt(xmlTag.getAttribute("id")),
                xmlTag.getAttribute("label"),
                xmlTag.getAttribute("description"),
                ColorUtil.getColor(xmlTag.getAttribute("color")),
                0);
    }

    public int getStatusEffect() {
        return statusEffect;
    }

    public void setStatusEffect(int statusEffect) {
        this.statusEffect = statusEffect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLocalisationId() {
        return nameLocalisationId;
    }

    public void setNameLocalisationId(String nameLocalisationId) {
        this.nameLocalisationId = nameLocalisationId;
    }

    public String getDescriptionLocalisationId() {
        return descriptionLocalisationId;
    }

    public void setDescriptionLocalisationId(String descriptionLocalisationId) {
        this.descriptionLocalisationId = descriptionLocalisationId;
    }

    public GUI_Field getGuiField() {
        return guiField;
    }

    public void setGuiField(GUI_Field guiField) {
        this.guiField = guiField;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
