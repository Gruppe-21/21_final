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
        updateView();

    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
        if (getOwner() == null) {
            if (!playerController.purchaseProperty(this, model.getPrice())) {
                //Auction
            }
        } else if (getOwner() != playerController) {
            if (!getOwner().getStatusEffects().isImprisoned())
                playerController.transferMoney(model.getRent(), getOwner());
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

    /**
     * Sells this property to the bank
     */
    public void sell(){
        this.getOwner().addBalance(model.getPrice());
        this.getOwner().removeOwnedProperty(this);
        this.model.setOwner(null);
        //TODO: auction
    }

    public boolean isMortgaged(){
        return model.isMortgaged();
    }

    public void mortgage(){
        if (isMortgaged()) return;
        model.setMortgaged(true);
        getOwner().addBalance(model.getPrice() / 2);
    }

    public void setGroup(OwnableSquareController[] group){
        model.setGroup(group);
    }




}
