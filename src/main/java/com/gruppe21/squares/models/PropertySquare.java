package com.gruppe21.squares.models;

import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.utils.ColorUtil;
import gui_fields.GUI_Street;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;

public class PropertySquare extends OwnableSquare {
    private int maxNumHouses;
    private int houses;
    private int buildingCost;
    private String groupColor;

    public PropertySquare(int id, String label, String description, Color color, int statusEffect, int price, int buildingCost, String group, int... rent) {
        super(id, label, description, color, statusEffect, price);
        this.buildingCost = buildingCost;
        this.rent = rent;
        maxNumHouses = rent.length - 1;
        houses = 0;
        GUI_Street street = new GUI_Street();
        setGuiField(street);
        this.groupColor = group;
    }

    public PropertySquare(Element xmlTag) {
        this(parseInt(xmlTag.getAttribute("id")), //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0, // StatusEffect
                !xmlTag.getAttribute("buildingCost").equals("") ? parseInt(xmlTag.getAttribute("buildingCost")) : 0,
                parseInt(xmlTag.getAttribute("price")),  // price
                xmlTag.getAttribute("color"),
                readRent(xmlTag)

        );
    }

    public GUI_Street getGuiField() {
        return (GUI_Street) (super.getGuiField());
    }

    public void setGuiField(GUI_Street guiField) {
        super.setGuiField(guiField);
    }

    public int getMaxNumHouses() {
        return maxNumHouses;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
        if (this.houses < 0) this.houses = 0;
    }

    public void addHouse(int numHouses) {
        setHouses(getHouses() + numHouses);
    }

    public int getBuildingCost() {
        return buildingCost;
    }

    public int getRent() {
        if (owner == null) return 0; //This should never happen
        if (houses == 0) {
            for (OwnableSquareController squareController : group) {
                if (squareController.getOwner() != getOwner()) return rent[0];
            }
            return rent[0] * 2;
        }
        return rent[houses];
    }

    public String getGroupColor() {
        return groupColor;
    }


}
