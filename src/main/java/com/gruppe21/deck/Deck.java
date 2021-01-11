package com.gruppe21.deck;

import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.utils.CardLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.xml.sax.SAXException;

import java.util.Arrays;
import java.util.Random;

public class Deck {
    private final int TOTAL_CARDS;
    private CardController[] cards = new CardController[32];
    private int cardsDrawn = 0;

    /**
     * Deck constructor which loads cards from fileName into an array called cards
     */
    public Deck(String fileName) {
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
    public Deck() {
        CardController[] cards = new CardController[0];
        TOTAL_CARDS = 0;
    }



    /**
     * Draw first card from deck
     * @return card
     */
    public CardController nextCard() {
        CardController card = cards[0]; // Top cards/first card

        if (cardsDrawn == TOTAL_CARDS) shuffleDeck();
        cardsDrawn++;

        removeCard(card);

        return card;
    }

    /**
     * Remove one card of certain CardController type
     * @param removeCard
     */
    public void removeCard(CardController removeCard) {
        int removeIndex = 0;
        CardController[] cardsCopy = new CardController[cards.length-1];
        boolean contains = Arrays.stream(cards).anyMatch(removeCard::equals);

        // Finds index of removeCard in cards array
        if (contains) {
            for(int i = 0; i < cards.length; i++){
                if(removeCard == cards[i]){
                    removeIndex = i;
                    break;
                }
            }

            // Sorts removeCard out of cards array
            int i=0,j=0;
            while(i < cards.length){
                if(cards[i] != cards[removeIndex]){
                    cardsCopy[j] = cards[i];
                    j++;
                }
                i++;
            }
            this.cards = cardsCopy;
        }

    }

    /**
     * Returns a card (putBackCard) to deck
     * @param putBackCard
     */
    public void returnCard(CardController putBackCard) {
        CardController[] cardsCopy = new CardController[cards.length + 1];
        for (int i = 0; i < cards.length; i++) {
            cardsCopy[i] = cards[i];
            if (cards[i] == cards[cards.length - 1])
                cardsCopy[i + 1] = putBackCard;
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
            CardController tempController;
            int rand_int = rand.nextInt(cards.length);
            tempController = cards[i];
            cards[i] = cards[rand_int];
            cards[rand_int] = tempController;
        }
    }
}
