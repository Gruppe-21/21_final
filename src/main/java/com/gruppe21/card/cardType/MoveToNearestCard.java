package com.gruppe21.card.cardType;

import org.w3c.dom.Element;
import static java.lang.Integer.parseInt;

public class MoveToNearestCard extends Card{
    int[] IDSquares;

    public MoveToNearestCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int... IDSquares) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.IDSquares = IDSquares;
    }

    public MoveToNearestCard(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"),
                xmlTag.getAttribute("onUseDescription"),
                readIds(xmlTag)
                );
    }

    private static int[] readIds(Element xmlTag) {
        String[] idStr = xmlTag.getAttribute("squareID").split(" ");
        int[] ids = new int[idStr.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = parseInt(idStr[i]);
        }
    }

    public int[] getIDSquares() {
        return IDSquares;
    }

}
