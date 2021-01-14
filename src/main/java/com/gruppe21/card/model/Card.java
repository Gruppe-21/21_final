package com.gruppe21.card.model;

import com.gruppe21.deck.Deck;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public abstract class Card {
    protected String descriptionOnUseLabel;
    protected Deck deckToReturnTo;
    protected final int statusEffect;

    public Card(String descriptionOnUseLabel, int statusEffect) {
        this.descriptionOnUseLabel = descriptionOnUseLabel;
        this.statusEffect = statusEffect;
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

    public int getStatusEffect(){
        return statusEffect;
    }

    protected static int readStatusEffect(Element xmlTag){
        String statusEffectString = xmlTag.getAttribute("statusEffect");
        return statusEffectString.isEmpty() ? 0 : parseInt(statusEffectString);
    }

}
