package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.OwnableSquare;
import com.gruppe21.squares.views.OwnableSquareView;

public class OwnableSquareController extends SquareController {
    OwnableSquare model;
    OwnableSquareView view;

    public OwnableSquareController(OwnableSquare model, OwnableSquareView view) {
        super(model, view);
        this.model = model;
        this.view = view;
    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        if (getOwner() == null) {
            if (!playerController.purchaseProperty(this, model.getPrice())) {
                //Auction
            }
        } else if (getOwner() != playerController) {
            playerController.transferMoney(model.getRent(), getOwner());
        }
        else{
            super.onMoveTo(playerController);
        }
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

    /**
     * Returns a boolean indicating if buildings may be build on the property.
     * @return boolean indicating if buildings may be build on the property.
     */
    public boolean mayBuild(){
        return false;
    }

    public boolean isMortgaged(){
        return model.isMortgaged();
    }

    public void mortgage(){
        if (isMortgaged()) return;
        model.setMortgaged(false);
        getOwner().addBalance(model.getPrice());
    }
}
