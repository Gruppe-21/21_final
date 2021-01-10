package com.gruppe21.squares.models;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.PropertySquareController;
import org.w3c.dom.Element;

import java.awt.*;

public abstract class OwnableSquare extends Square {


    private int price;
    private int[] rent;
    private PropertySquareController group;

    public OwnableSquare(int id, String nameLabel, String descriptionLabel, Color color, int statusEffect, int price) {
        super(id, nameLabel, descriptionLabel, color, statusEffect);
    }

    public OwnableSquare(Element xmlTag) {
        super(xmlTag);
    }

    abstract public int getPrice();

    abstract public int getRent();

    abstract public PlayerController getOwner();
    abstract public void setOwner();

    abstract public void setGroup(PropertySquareController[] group);
}
