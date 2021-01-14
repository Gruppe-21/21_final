package com.gruppe21.card.moveCards.model;

import com.gruppe21.card.model.Card;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class MoveRelativeCard extends Card {
    private final int moveSquares;


    public MoveRelativeCard(String descriptionOnUseLabel, int statusEffect, int moveSquares) {
        super(descriptionOnUseLabel, statusEffect);
        this.moveSquares = moveSquares;
    }

    public MoveRelativeCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                readStatusEffect(xmlTag),
                parseInt(xmlTag.getAttribute("moveSquares"))
        );
    }


    public int getMoveSquares() {
        return moveSquares;
    }

}
