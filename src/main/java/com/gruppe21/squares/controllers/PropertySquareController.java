package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.PropertySquare;
import com.gruppe21.squares.views.PropertySquareView;

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

    @Override
    public boolean mayBuild(){
        if (this.getOwner() == null) return false;
        for (OwnableSquareController property: model.getGroup()) {
            if (property.getOwner() != this.getOwner()){
                return false;
            }
        }
        return true;
    }

}
