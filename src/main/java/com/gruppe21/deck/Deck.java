package com.gruppe21.deck;

import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.utils.CardLoader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.xml.sax.SAXException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

public class Deck {
    private int totalCards;
    private int currentNumCards;
    private CardController[] cards = new CardController[32];
    private CardController[] returnedCards;
    private int numCardsReturned = 0;
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
        totalCards = cards.length;
        currentNumCards = totalCards;
        returnedCards = new CardController[totalCards];
    }

    /**
     * Deck constructor which has an empty array of CardControllers
     */
    public Deck() {
        CardController[] cards = new CardController[0];
        totalCards = 0;
        currentNumCards = 0;
        returnedCards = new CardController[0];
    }


    public CardController drawCardOfClass(Class cardClass){
        for (int i = cardsDrawn; i < currentNumCards; i++) {
            if(cards[i].getCardClass().equals(cardClass)){
                CardController drawnCard = cards[i];
                cards[i] = cards[cardsDrawn];
                cards[cardsDrawn] = cards[i];
                cardsDrawn++;
                return cards[i];
            }
        }
        return null;
    }

    /**
     * Draw first card from deck
     * @return top card
     */
    public CardController nextCard() {
        if (cardsDrawn == currentNumCards) {
            cards = returnedCards;
            currentNumCards = numCardsReturned;
            numCardsReturned = 0;
            shuffleDeck();
            cardsDrawn = 0;
        }
        CardController card = cards[cardsDrawn]; // Top cards/first card
        cardsDrawn++;
        return card;
    }

    /**
     * Remove a specific card from the deck
     * @param removeCard
     */
    public void removeCard(CardController removeCard) {
        boolean contains = Arrays.stream(cards).anyMatch(removeCard::equals);
        if (!contains) return;

        int removeIndex = 0;
        CardController[] cardsCopy = new CardController[cards.length - 1];
        // Finds index of removeCard in cards array
        for (int i = 0; i < cards.length; i++) {
            if (removeCard == cards[i]) {
                removeIndex = i;
                break;
            }
        }
        // Sorts removeCard out of cards array
        int i = 0, j = 0;
        while (i < cards.length) {
            if (cards[i] != cards[removeIndex]) {
                cardsCopy[j] = cards[i];
                j++;
            }
            i++;
        }
        this.cards = cardsCopy;
        totalCards--;
        currentNumCards--;
        if (removeIndex < cardsDrawn) cardsDrawn--;
    }

    /**
     * Adds a card to the bottom of the deck.
     * @param card the card to be added
     */
    public void addCard(CardController card){
        totalCards++;
        currentNumCards++;
        CardController[] cardControllers = new CardController[totalCards];
        for (int i = 0; i < cards.length; i++) {
            cardControllers[i] = cards[i];
        }
        cardControllers[totalCards - 1] = card;
        cards = cardControllers;
    }

    /**
     * Returns a card to deck.
     * @param card card to be returned
     */
    public void returnCard(CardController card) {
        returnedCards[numCardsReturned] = card;
        numCardsReturned++;
    }

    /**
     * Shuffles the cards in a random order
     */
    public void shuffleDeck() {
        cardsDrawn = 0;
        Random rand = new Random();
        for (int i = 0; i < currentNumCards; i++) {
            CardController tempController;
            int rand_int = rand.nextInt(currentNumCards);
            tempController = cards[i];
            cards[i] = cards[rand_int];
            cards[rand_int] = tempController;
        }
    }
}
