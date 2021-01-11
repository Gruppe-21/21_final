package com.gruppe21.squares.models;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import gui_fields.GUI_Ownable;
import org.w3c.dom.Element;

import java.awt.*;

public abstract class OwnableSquare extends Square {
    protected int price;
    protected int[] rent;
    protected PlayerController owner; //owner = null -> owner is the bank.
    protected OwnableSquareController[] group;


    public OwnableSquare(int id, String nameLabel, String descriptionLabel, Color color, int statusEffect, int price) {
        super(id, nameLabel, descriptionLabel, color, statusEffect);
        this.price = price;
        this.owner = null;
    }

    public OwnableSquare(Element xmlTag) {
        super(xmlTag); //??
    }

    public GUI_Ownable getGuiField() {
        return (GUI_Ownable) (super.getGuiField());
    }

    public void setGuiField(GUI_Ownable guiField){
        super.setGuiField(guiField);
    }

    public int getPrice(){
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    abstract public int getRent();

    public PlayerController getOwner(){
        return owner;
    }
    public void setOwner(PlayerController owner){
        this.owner = owner;
    }

    protected void setGroup(OwnableSquareController[] group){
        this.group = group;
    }
}
