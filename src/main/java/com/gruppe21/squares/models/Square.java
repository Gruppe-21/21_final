package com.gruppe21.squares.models;

import gui_fields.GUI_Empty;
import gui_fields.GUI_Field;

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



    public Square(int statusEffect, int id) {
        this.statusEffect = statusEffect;
        this.id = id;
        this.guiField = new GUI_Empty();
    }

    public int getStatusEffect() { return statusEffect; }

    public void setStatusEffect(int statusEffect) { this.statusEffect = statusEffect; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNameLocalisationId() { return nameLocalisationId; }

    public void setNameLocalisationId(String nameLocalisationId) { this.nameLocalisationId = nameLocalisationId; }

    public String getDescriptionLocalisationId() { return descriptionLocalisationId; }

    public void setDescriptionLocalisationId(String descriptionLocalisationId) { this.descriptionLocalisationId = descriptionLocalisationId; }

    public GUI_Field getGuiField() {
        return guiField;
    }

    public void setGuiField(GUI_Field guiField) {
        this.guiField = guiField;
    }
}
