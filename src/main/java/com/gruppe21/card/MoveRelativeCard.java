package com.gruppe21.card;

public class MoveRelativeCard extends Card{
    private int id; //squareIndex?

    public MoveRelativeCard(String descriptionOnDrawLabel,String descriptionOnUseLabel,int id) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.id = id;
    }
}
