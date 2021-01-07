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
     * Identifier used for getting the localized string
     */
    private String localisationId;

    public Square(int statusEffect, int id) {
        this.statusEffect = statusEffect;
        this.id = id;
    }

    public int getStatusEffect() { return statusEffect; }

    public void setStatusEffect(int statusEffect) { this.statusEffect = statusEffect; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getLocalisationId() { return localisationId; }

    public void setLocalisationId(String localisationId) { this.localisationId = localisationId; }

}
