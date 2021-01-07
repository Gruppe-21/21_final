package com.gruppe21.deck;

import com.gruppe21.card.typeOfCards.Card;
import com.gruppe21.utils.CardLoader;
import jdk.internal.org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Random;

public class Deck {
    // private CardController[] cards = new CardController[32];
    private final int TOTAL_CARDS; // Antal af kort i alt
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
     *
     *  */
    public Card drawCard(){
        CardController[] card = cards[0];

        if (cardsDrawn == TOTAL_CARDS) shuffleDeck();
        cardsDrawn++;

        Card[] cardsCopy = new Card[cards.length-1];
        for(int i = 0, j = 1; j<cards.length; i++, j++){
            cardsCopy[i] = cards[j];
        }
        return card;
    }


    public void returnCard(--- returnCard){

    }

    public void shuffleDeck(){
        //Noget med for-loop + random


        //lastShuffle = 0;
        Random rand = new Random();
        for(int i = 0; i < cards.length; i++) {
            int rand_int = rand.nextInt(6);
            ChanceCard temp; //Origin?
            temp = cards[i];
            cards[rand_int];
            //Hmmmmm....
            //temp = cards.get(i);
            //cards.set(i, cards.get(rand_int));
            //cards.set(rand_int, temp);
            return;
        }
    }


}
