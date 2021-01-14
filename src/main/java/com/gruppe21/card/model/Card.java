package com.gruppe21.card.model;

import com.gruppe21.deck.Deck;
import org.w3c.dom.Element;

public abstract class Card {
    protected String descriptionOnUseLabel;
    protected Deck deckToReturnTo;

    public Card(String descriptionOnUseLabel) {
        this.descriptionOnUseLabel = descriptionOnUseLabel;
    }


    public String getDescriptionOnUseLabel() {
        return descriptionOnUseLabel;
    }

    public void setDeckToReturnTo(Deck deckToReturnTo){
        this.deckToReturnTo = deckToReturnTo;
    }

    public Deck getDeckToReturnTo(){
        return deckToReturnTo;
    }

}
