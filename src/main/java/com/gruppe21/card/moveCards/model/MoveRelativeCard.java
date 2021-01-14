package com.gruppe21.card.moveCards.model;

import com.gruppe21.card.model.Card;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class MoveRelativeCard extends Card {
    private final int moveSquares;


    public MoveRelativeCard(String descriptionOnUseLabel, int moveSquares) {
        super(descriptionOnUseLabel);
        this.moveSquares = moveSquares;
    }

    public MoveRelativeCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                parseInt(xmlTag.getAttribute("moveSquares"))
        );
    }


    public int getMoveSquares() {
        return moveSquares;
    }

}
