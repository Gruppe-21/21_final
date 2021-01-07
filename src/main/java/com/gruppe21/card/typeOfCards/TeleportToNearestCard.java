package com.gruppe21.card.typeOfCards;

import com.gruppe21.card.typeOfCards.Card;

public class TeleportToNearestCard extends Card {
    private final int id; //squareIndex prison?

    public TeleportToNearestCard(String descriptionOnDrawLabel, String descriptionOnUseLabel,int id) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.id = id;
    }
}
