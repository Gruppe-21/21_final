package com.gruppe21.card.moneyCards.model;

import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class GrantCard extends ModifyMoneyCard {
    private final int maxOwnedValue;

    public GrantCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue, int maxOwnedValue) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel, modifyValue);
        this.maxOwnedValue = maxOwnedValue;
    }

    public GrantCard(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"),
                xmlTag.getAttribute("onUseDescription"),
                parseInt(xmlTag.getAttribute("modifyValue")),
                parseInt(xmlTag.getAttribute("maxOwnedValue"))
        );
    }

    public int getMaxOwnedValue() {
        return maxOwnedValue;
    }
}
