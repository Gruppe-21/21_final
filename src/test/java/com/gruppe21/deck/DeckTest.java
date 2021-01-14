package com.gruppe21.deck;

import com.gruppe21.card.cardControllers.controllers.CardController;
import com.gruppe21.card.moneyCards.controllers.MoneyCardController;
import com.gruppe21.utils.CardLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck("cards");
    }

    @AfterEach
    void tearDown() {
        deck = null;
        CardLoader.cardsAdded = 0;
    }
/*
    @Test
    void drawCardOfClass() {
        deck.drawCardOfClass()
    }
*/
    @Test
    void nextCard() {
        assertSame(deck.nextCard().getCardClass(), MoneyCardController.class);
    }

    @Test
    void removeCard() {
        // Need to find a way to test this
    }

    @Test
    void addCard() {
        // Can't test. No access to data.
    }

    @Test
    void returnCard() {
        // Can't test. No access to data.
    }

    @Test
    void shuffleDeck() {
        // Can't test. No access to data.
    }
}