package com.gruppe21.squares.models;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import gui_fields.GUI_Ownable;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public abstract class OwnableSquare extends Square {
    protected int price;
    protected int[] rent;
    protected boolean mortgaged = false;
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

    public int getMortgageValue(){
        return getPrice() / 2;
    }


    abstract public int getRent();

    public boolean isMortgaged(){
        return mortgaged;
    }

    public void setMortgaged(boolean mortgaged){
        this.mortgaged = mortgaged;
    }

    public PlayerController getOwner(){
        return owner;
    }
    public void setOwner(PlayerController owner){
        this.owner = owner;
    }

    public void setGroup(OwnableSquareController[] group){
        this.group = group;
    }

    public OwnableSquareController[] getGroup(){
        return group;
    }

    static public int[] readRent(Element tag) {
        //  String[] rentStr = tag.getAttribute("rent").split(" ");
        // int[] rent = new int[rentStr.length];
        // for (int i = 0; i < rent.length; i++) {
        //     rent[i] = parseInt(rentStr[i]);
        // }
        int[] rent = new int[6];
        rent[0] = !tag.getAttribute("rent_base").equals("") ? parseInt(tag.getAttribute("rent_base")) : 0;
        rent[1] = !tag.getAttribute("rent_1house").equals("") ? parseInt(tag.getAttribute("rent_1house")) : 0;
        rent[2] = !tag.getAttribute("rent_2house").equals("") ? parseInt(tag.getAttribute("rent_2house")) : 0;
        rent[3] = !tag.getAttribute("rent_3house").equals("") ? parseInt(tag.getAttribute("rent_3house")) : 0;
        rent[4] = !tag.getAttribute("rent_4house").equals("") ? parseInt(tag.getAttribute("rent_4house")) : 0;
        rent[5] = !tag.getAttribute("rent_hotel").equals("") ? parseInt(tag.getAttribute("rent_hotel")) : 0;

        return rent;
    }
}
