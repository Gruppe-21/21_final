package com.gruppe21.card.typeOfCards;

public class ModifyMoneyCard extends Card {
    private int money;

    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int money) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.money = money;
    }
}
