package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.Card;
import com.gruppe21.deck.Deck;
import com.gruppe21.game.Board;
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
    public abstract void onDraw(PlayerController drawer);

    /**
     * Method that display the cards description
     * @param user Player that uses card
     */
    public void use(PlayerController user){
        if (card == null || cardView == null) return; //This should never happen
        cardView.displayCard(card.getDescriptionOnUseLabel());
    }

    // Used for cards with connection to board. blah blah
    public void use(PlayerController user, Board board){
        cardView.displayCard(card.getDescriptionOnUseLabel());
    }







    /**
     * Returns the {@code Class} object of the {@code CardController}'s {@code Card}
     * @return the {@code Class} object of the card
     */
    public Class getCardClass(){
        return card.getClass();
    }

}
