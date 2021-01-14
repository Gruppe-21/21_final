package com.gruppe21.card.cardControllers.controllers;

import com.gruppe21.card.view.CardView;
import com.gruppe21.card.model.Card;
import com.gruppe21.deck.Deck;
import com.gruppe21.player.PlayerController;



public abstract class CardController {
    protected CardView cardView;
    protected Card card;

    public CardController(CardView cardView, Card card){
        this.cardView = cardView;
        this.card = card;
    }

    public Deck getReturnDeck(){
        return card.getDeckToReturnTo();
    }

    public void setReturnDeck(Deck deck){
        card.setDeckToReturnTo(deck);
    }

    /**
     * @param drawer Player that draws card
     */
    public void onDraw(PlayerController drawer){
        use(drawer);
    }

    /**
     * Method that display the cards description
     * @param user Player that uses card
     */
    public void use(PlayerController user){
        cardView.displayCard(card.getDescriptionOnUseLabel());
        returnToDeck(user);
    }

    protected void returnToDeck(PlayerController user){
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }


    /**
     * Returns the {@code Class} object of the {@code CardController}'s {@code Card}
     * @return the {@code Class} object of the card
     */
    public Class getCardClass(){
        return card.getClass();
    }

}
