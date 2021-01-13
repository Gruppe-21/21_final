package com.gruppe21.card.cardType;

import com.gruppe21.squares.controllers.SquareController;

public class MoveToNearestCard extends Card{
    int[] IDSquares;

    public MoveToNearestCard(String descriptionOnDrawLabel, String descriptionOnUseLabel,int[] IDSquares) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.IDSquares = IDSquares;
    }

    public int[] getIDSquares() {
        return IDSquares;
    }

}
