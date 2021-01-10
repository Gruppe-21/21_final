package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.PropertySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.PropertySquareView;
import com.gruppe21.squares.views.SquareView;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Street;

public class PropertySquareController extends OwnableSquareController {
    PropertySquare model;
    PropertySquareView view;
    public PropertySquareController(PropertySquare model, PropertySquareView view) {
        super(model, view);
    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
    }


    public void addHouse(){
        if (model.getHouses() == model.getMaxNumHouses()) return; //Throw exception?
        model.addHouse(1);
        view.updateHouses(model);
    }

    public int getBuildingCost(){
        return model.getBuildingCost();
    }

}
