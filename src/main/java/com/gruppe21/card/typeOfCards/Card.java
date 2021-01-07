package com.gruppe21.card.typeOfCards;

public abstract class Card {
    private String descriptionOnDrawLabel;
    private String descriptionOnUseLabel;

    public Card(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
        this.descriptionOnUseLabel = descriptionOnUseLabel;
    }
}
