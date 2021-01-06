package com.gruppe21.squares.models;

public class Square {
    private int statusEffect;
    private int id;

    public Square(int statusEffect, int id) {
        this.statusEffect = statusEffect;
        this.id = id;
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
}
