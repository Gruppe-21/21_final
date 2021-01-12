package com.gruppe21.squares.controllers;

import com.gruppe21.game.GameController;
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
        updateView();
        view.updateRent(model);
    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
    }

    public int getMaxNumHouses(){
        return model.getMaxNumHouses();
    }

    public int getNumHouses(){
        return model.getHouses();
    }

    public void addHouse(){
        if (model.getHouses() == model.getMaxNumHouses()) return; //Throw exception?
        if (getNumHouses() == getMaxNumHouses()-1) GameController.getInstance().getHotel(1);
        else GameController.getInstance().getHouses(1);
        model.addHouse(1);
        view.updateHouses(model);
    }
    
    public void sellHouses(int numHouses){
        if (numHouses < 1) return;
        if (numHouses > getNumHouses()) { //We can't have a negative number of buildings
            numHouses = getNumHouses();
        }
        else if (getNumHouses() == model.getMaxNumHouses()) { //If we have a hotel, we must sell the entire thing
            numHouses = getNumHouses();
            GameController.getInstance().getHotels(-1);
        }
        else GameController.getInstance().getHouses(-numHouses);
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
        if (((this.getNumHouses() == this.getMaxNumHouses()-1) && GameController.getInstance().getAvailableHotels() == 0) || //We want a hotel but there are none
                ((this.getNumHouses() != this.getMaxNumHouses()-1)) && GameController.getInstance().getAvailableHouses() == 0) //We want a house but there are none
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
