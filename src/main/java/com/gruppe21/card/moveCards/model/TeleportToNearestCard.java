package com.gruppe21.card.moveCards.model;

import com.gruppe21.card.model.Card;
import com.gruppe21.squares.controllers.SquareController;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class TeleportToNearestCard extends Card {
    int[] squareIDs;
    SquareController[] possibleDestinations;

    public TeleportToNearestCard(String descriptionOnUseLabel, int statusEffect, int... squareIDs) {
        super(descriptionOnUseLabel, statusEffect);
        this.squareIDs = squareIDs;
    }

    public TeleportToNearestCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                readStatusEffect(xmlTag),
                readIds(xmlTag)
        );
    }

    private static int[] readIds(Element xmlTag) {
        String[] idStr = xmlTag.getAttribute("squareID").split(" ");
        int[] ids = new int[idStr.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = parseInt(idStr[i]);
        }
        return ids;
    }

    public int[] getSquareIDs() {
        return squareIDs;
    }

    public SquareController[] getPossibleDestinations(){
        return possibleDestinations;
    }

    public void setPossibleDestinations(SquareController[] possibleDestinations) {
        this.possibleDestinations = possibleDestinations;
    }
}
