package com.gruppe21.card.typeOfCards;

import com.gruppe21.deck.Deck;

public abstract class Card {
    protected String descriptionOnDrawLabel;
    protected String descriptionOnUseLabel;
    protected Deck deckToReturnTo;

    public Card(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
        this.descriptionOnUseLabel = descriptionOnUseLabel;
    }

    public String getDescriptionOnDrawLabel() {
        return descriptionOnDrawLabel;
    }

    public String getDescriptionOnUseLabel() {
        return descriptionOnDrawLabel;
    }

    public void setDeckToReturnTo(Deck deckToReturnTo){
        this.deckToReturnTo = deckToReturnTo;
    }
    public Deck getDeckToReturnTo(){
        return deckToReturnTo;
    }

}
