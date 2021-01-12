package com.gruppe21.card.cardType;

public class MoveRelativeCard extends Card {
    private int moveSquares;


    public MoveRelativeCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int moveSquares) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.moveSquares = moveSquares;
    }


    public int getMoveSquares() {
        return moveSquares;
    }

}
