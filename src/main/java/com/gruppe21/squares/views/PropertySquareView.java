package com.gruppe21.squares.views;

import com.gruppe21.squares.models.PropertySquare;
import gui_fields.GUI_Street;

import java.awt.*;

public class PropertySquareView extends OwnableSquareView{

    public void updateHouses(PropertySquare model){
        if (model.getHouses() == model.getMaxNumHouses()){
            model.getGuiField().setHouses(0);
            model.getGuiField().setHotel(true);
        }
        else model.getGuiField().setHouses(model.getHouses());
        updateRent(model);
    }


}
