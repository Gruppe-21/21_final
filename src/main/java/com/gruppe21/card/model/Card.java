package com.gruppe21.card.model;

import com.gruppe21.deck.Deck;
import org.w3c.dom.Element;

public class Card {
    protected String descriptionOnDrawLabel;
    protected String descriptionOnUseLabel;
    protected Deck deckToReturnTo;

    public Card(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
        this.descriptionOnUseLabel = descriptionOnUseLabel;
    }

    public Card(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"), xmlTag.getAttribute("onUseDescription"));
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
