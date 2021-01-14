package com.gruppe21.card.moneyCards.model;

import com.gruppe21.card.model.Card;

public class ModifyMoneyCard extends Card {
    private final int modifyValue;

    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
    }



    public int getModifyValue() {
        return modifyValue;
    }

}
