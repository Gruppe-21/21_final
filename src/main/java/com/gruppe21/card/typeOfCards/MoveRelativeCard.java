package com.gruppe21.card.typeOfCards;

public class MoveRelativeCard extends Card {
    private int squareID;


    public MoveRelativeCard(String descriptionOnDrawLabel,String descriptionOnUseLabel,int squareID) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.squareID = squareID;
    }

    public int getSquareID() {
        return squareID;
    }
}
