package com.gruppe21.game.board.Deck;
import java.util.Random;

import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.utils.BoardLoader;
import com.gruppe21.utils.arrayutils.OurArrayList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Deck {

    private OurArrayList <ChanceCard> cards;
    private Random rand = new Random();

    public Deck(){
        try {
            cards = BoardLoader.loadCards("cards");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    public void shuffle()
    {for(int i = 1; i < cards.size(); i++) {
        int rand_int = rand.nextInt(6);
        ChanceCard temp;
        temp = cards.get(i);
        cards.get(i) = cards.get(rand_int);
        cards.get(rand_int) = temp;
    }
}

//Der er add, get, remove and size