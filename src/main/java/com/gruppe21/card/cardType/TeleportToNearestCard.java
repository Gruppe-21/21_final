package com.gruppe21.card.cardType;

import com.gruppe21.squares.controllers.SquareController;

public class TeleportToNearestCard extends Card {
    private int squareIDs; //squareIndex prison?
    SquareController[] possibleDestinations;

    public TeleportToNearestCard(String descriptionOnDrawLabel, String descriptionOnUseLabel,int squareIDs) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.squareIDs = squareIDs;
    }

    public int getSquareIDs() {
        return squareIDs;
    }

    public SquareController[] getPossibleDestinations(){
        return possibleDestinations
    }

    public void setPossibleDestinations(SquareController[] possibleDestinations) {
        this.possibleDestinations = possibleDestinations;
    }
}
