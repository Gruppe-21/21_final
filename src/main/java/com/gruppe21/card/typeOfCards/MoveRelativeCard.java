package com.gruppe21.card.typeOfCards;

public class MoveRelativeCard extends Card {
    private int square_ID; //squareIndex?

    public MoveRelativeCard(String descriptionOnDrawLabel,String descriptionOnUseLabel,int square_ID) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.square_ID = square_ID;
    }


    public int getSquare_ID() {
        return square_ID;
    }
}
