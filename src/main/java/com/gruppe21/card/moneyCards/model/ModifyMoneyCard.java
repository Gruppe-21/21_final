package com.gruppe21.card.moneyCards.model;

import com.gruppe21.card.model.Card;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class ModifyMoneyCard extends Card {
    private final int modifyValue;

    public ModifyMoneyCard(String descriptionOnUseLabel, int modifyValue) {
        super(descriptionOnUseLabel);
        this.modifyValue = modifyValue;
    }

    public ModifyMoneyCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                parseInt(xmlTag.getAttribute("modifyValue"))
        );
    }

    public int getModifyValue() {
        return modifyValue;
    }

}
