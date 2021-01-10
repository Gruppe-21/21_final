package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.PropertySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.PropertySquareView;
import com.gruppe21.squares.views.SquareView;
import gui_fields.GUI_Empty;
import gui_fields.GUI_Street;

public class PropertySquareController extends SquareController {
    PropertySquare model;
    PropertySquareView view;
    public PropertySquareController(PropertySquare model, PropertySquareView view) {
        super(model, view);
    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
    }


    public PlayerController getOwner(){
        return model.getOwner();
    }

    /**
     * Removes the property from the old owners list of owned properties and adds it to the new owners list and sets the owner to the new owner.
     * @param newOwner the {@code PlayerController} of the new owner.
     */
    public void setOwner(PlayerController newOwner){
        if (model.getOwner() != null) model.getOwner().removeOwnedProperty(this);
        if (newOwner != null) newOwner.addOwnedProperty(this);
        model.setOwner(newOwner);
        view.updateOwner(model);
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
