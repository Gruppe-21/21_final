package com.gruppe21.squares.models;

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

    public Square(int statusEffect, int id) {
        this.statusEffect = statusEffect;
        this.id = id;
    }

    public int getStatusEffect() { return statusEffect; }

    public void setStatusEffect(int statusEffect) { this.statusEffect = statusEffect; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNameLocalisationId() { return nameLocalisationId; }

    public void setNameLocalisationId(String nameLocalisationId) { this.nameLocalisationId = nameLocalisationId; }

    public String getDescriptionLocalisationId() { return descriptionLocalisationId; }

    public void setDescriptionLocalisationId(String descriptionLocalisationId) { this.descriptionLocalisationId = descriptionLocalisationId; }
}
