package com.gruppe21.card.cardType;

public class MoveToSquareCard extends Card {
    private int squareID;

    public MoveToSquareCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int squareID) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.squareID = squareID;
    }

    public int getSquareID() {
        return squareID;
    }

}
