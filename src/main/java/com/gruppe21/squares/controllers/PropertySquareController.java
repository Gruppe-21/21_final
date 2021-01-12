package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.PropertySquare;
import com.gruppe21.squares.views.PropertySquareView;

public class PropertySquareController extends OwnableSquareController {
    PropertySquare model;
    PropertySquareView view;
    public PropertySquareController(PropertySquare model, PropertySquareView view) {
        super(model, view);
        this.model = model;
        this.view = view;
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
    
    public void sellHouses(int numHouses){
        if (numHouses < 1) return;
        if (numHouses > getNumHouses()) numHouses = getNumHouses(); //We can't have a negative number of buildings
        else if (getNumHouses() == model.getMaxNumHouses()) numHouses = getNumHouses(); //If we have a hotel, we must sell the entire thing

        model.setHouses(getNumHouses() - numHouses);
        getOwner().addBalance((getBuildingCost() *numHouses)/2);
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

    @Override
    public void sell() {
        for (PropertySquareController property: (PropertySquareController[]) model.getGroup()) {
            property.sellHouses(property.getNumHouses());
        }
        sellHouses(getNumHouses());
        super.sell();
    }

    @Override
    public void mortgage(){
        if (isMortgaged()) return;
        sellHouses(getNumHouses());
        super.mortgage();
    }

    public String getGroupColor() {
        return model.getGroupColor();
    }

}
