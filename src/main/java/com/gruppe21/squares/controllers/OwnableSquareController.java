package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.OwnableSquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.OwnableSquareView;
import com.gruppe21.squares.views.SquareView;

public abstract class OwnableSquareController extends SquareController {
    OwnableSquare model;
    OwnableSquareView view;

    public OwnableSquareController(Square model, SquareView view) {
        super(model, view);
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
}
