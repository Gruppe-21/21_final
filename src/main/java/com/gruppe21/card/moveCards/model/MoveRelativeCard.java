package com.gruppe21.card.moveCards.model;

import com.gruppe21.card.cardType.Card;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class MoveRelativeCard extends Card {
    private final int moveSquares;


    public MoveRelativeCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int moveSquares) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.moveSquares = moveSquares;
    }

    public MoveRelativeCard(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"),
                xmlTag.getAttribute("onUseDescription"),
                parseInt(xmlTag.getAttribute("moveSquares"))
        );
    }


    public int getMoveSquares() {
        return moveSquares;
    }

}
