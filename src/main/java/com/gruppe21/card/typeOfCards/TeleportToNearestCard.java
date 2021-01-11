package com.gruppe21.card.typeOfCards;

import com.gruppe21.card.typeOfCards.Card;

public class TeleportToNearestCard extends Card {
    private int squareID; //squareIndex prison?

    public TeleportToNearestCard(String descriptionOnDrawLabel, String descriptionOnUseLabel,int squareID) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.squareID = squareID;
    }

    public int getSquareID() {
        return squareID;
    }

}
