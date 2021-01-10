package com.gruppe21.squares.views;

import com.gruppe21.squares.models.PropertySquare;
import gui_fields.GUI_Street;

import java.awt.*;

public class PropertySquareView extends SquareView{
    Color borderColor = Color.black;

    /**
     *
     * @param model
     */
    public void updateOwner(PropertySquare model){
        model.getGuiField().setBorder(borderColor, model.getOwner().getColor());
        updateRent(model);
    }

    public void updateHouses(PropertySquare model){
        if (model.getHouses() == model.getMaxNumHouses()){
            model.getGuiField().setHouses(0);
            model.getGuiField().setHotel(true);
        }
        else model.getGuiField().setHouses(model.getHouses());
        updateRent(model);
    }

    public void updateRent(PropertySquare model){
        model.getGuiField().setRent(Integer.toString(model.getRent()));
    }


}
