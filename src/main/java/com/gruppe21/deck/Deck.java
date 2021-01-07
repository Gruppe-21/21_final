package com.gruppe21.deck;
import com.gruppe21.card.cardControllers.CardController;
import com.gruppe21.card.typeOfCards.Card;
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

    public Deck(){
        try {
            cards = CardLoader.loadCards("cards");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        TOTAL_CARDS = cards.length();
    }

    /**
     * Draws a card from the card deck
     */
    public CardController drawCard(){
        CardController card = cards[0];

        if (cardsDrawn == TOTAL_CARDS) shuffleDeck();
        cardsDrawn++;

        CardController[] cardsCopy = new CardController[cards.length-1];
        for(int i = 0, j = 1; j<cards.length; i++, j++){
            cardsCopy[i] = cards[j];
        }
        return card;
    }

/*
Hvordan håndterer vores klasser et dynamisk array??
Til Marcus: Vi vidste ikke hvordan vi skulle programmere returnCard ):
 */
    /**
     *
      * @param putBack is the card that is returned to the deck
     */
    public void returnCard(CardController putBack){
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null){ //Check om arrayet er fyldt med kort?

            }

        }
    }

    /**
     * Shuffles the cards in a random way
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
