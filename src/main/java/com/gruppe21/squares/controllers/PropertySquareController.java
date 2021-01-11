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

    public int getNumHouses(){
        return model.getHouses();
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
        if (this.getOwner() == null || this.getNumHouses() >= model.getMaxNumHouses()) return false;
        for (PropertySquareController property: (PropertySquareController[]) model.getGroup()) {
            if (property.getOwner() != this.getOwner() || this.getNumHouses() - property.getNumHouses() > 0){
                return false;
            }
        }
        return true;
    }

}
