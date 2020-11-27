package com.gruppe21.game.board.Deck;
import java.util.Random;

import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.utils.BoardLoader;
import com.gruppe21.utils.arrayutils.OurArrayList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Deck {
    private final boolean SHUFFLE_CARDS;
    private final int CARDS_BEFORE_SHUFFLE;
    private int lastShuffle = 0;
    private OurArrayList <ChanceCard> cards;
    private final Random rand = new Random();

    public Deck(){
        SHUFFLE_CARDS = true; //Should be read from file.
        try {
            cards = BoardLoader.loadCards("cards");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        CARDS_BEFORE_SHUFFLE = cards.size();

    }

    public void shuffleDeck() {
        lastShuffle = 0;
        for(int i = 1; i < cards.size(); i++) {
            int rand_int = rand.nextInt(6);
            ChanceCard temp;
            temp = cards.get(i);
            cards.set(i, cards.get(rand_int));
            cards.set(rand_int, temp);
            return;
        }
    }

    /**
     * Draws the top card.
     * @return
     */
    public ChanceCard drawCard(){
        if (lastShuffle == CARDS_BEFORE_SHUFFLE) shuffleDeck();
         lastShuffle++;
         ChanceCard Chance = cards.get(0);
         cards.removeIndex(0);
         return Chance;
    }

    public void returnCard(ChanceCard putBack){
        cards.add(putBack);
    }
}

//Der er add, get, remove and size