package com.gruppe21.deck;
import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.utils.CardLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import java.util.Random;

public class Deck {
    private final int TOTAL_CARDS;
    private CardController[] cards = new CardController[32];
    private int cardsDrawn = 0;
    private boolean sinceLastShuffle;

    /**
     * Loads cards from fileName into an array called cards
     */
    public Deck(String fileName){
        try {
            cards = CardLoader.loadCards(fileName); //"cards"
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        TOTAL_CARDS = cards.length;
    }

    /**
     * Deck constructor which has an empty array of CardControllers
     */
    public Deck(){
        CardController[] cards = new CardController[0];
        TOTAL_CARDS = 0;
    }



    /**
     * Draws first card from deck
     * @return card
     */
    public CardController nextCard(){
        CardController card = cards[0]; // Top cards/first card

        if (cardsDrawn == TOTAL_CARDS) shuffleDeck();
        cardsDrawn++;

        CardController[] cardsCopy = new CardController[cards.length-1];
        for(int i = 0, j = 1; j<cards.length; i++, j++){
            cardsCopy[i] = cards[j];
        }
        return card;
    }

    /**
     * Returns a card (putBackCard) to deck
     * @param putBackCard
     */
    public void returnCard(CardController putBackCard) {
        CardController[] cardsCopy = new CardController[cards.length+1];
        for (int i = 0; i < cards.length; i++) {
            cardsCopy[i] = cards[i];
            if (cards[i] == cards[cards.length - 1])
                cardsCopy[i+1] = putBackCard;
        }
        this.cards = cardsCopy;
    }

    /**
     * Shuffles the cards in a random order
     */
    public void shuffleDeck() {
        cardsDrawn = 0;
        Random rand = new Random();
        for (int i = 0; i < cards.length; i++) {
            int rand_int = rand.nextInt(6);
            CardController[] temp = new CardController[32];
            temp[i] = cards[i];
            temp[rand_int] = cards[i];
            cards[i] = cards[rand_int];
        }
    }
}
