package com.gruppe21.card.moneyCards.model;

import com.gruppe21.card.model.Card;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class ModifyMoneyCard extends Card {
    private final int modifyValue;

    public ModifyMoneyCard(String descriptionOnUseLabel, int statusEffect, int modifyValue) {
        super(descriptionOnUseLabel, statusEffect);
        this.modifyValue = modifyValue;
    }

    public ModifyMoneyCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                readStatusEffect(xmlTag),
                parseInt(xmlTag.getAttribute("modifyValue"))
        );
    }

    public int getModifyValue() {
        return modifyValue;
    }

}
