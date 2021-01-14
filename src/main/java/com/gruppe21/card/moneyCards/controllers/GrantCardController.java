package com.gruppe21.card.moneyCards.controllers;

import com.gruppe21.card.moneyCards.model.GrantCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.player.PlayerController;

public class GrantCardController extends MoneyCardController{

    public GrantCardController(CardView view, GrantCard card) {
        super(view, card);
    }

    @Override
    protected void modifyMoney(PlayerController user){
        if (user.getTotalValue() >= getCard().getMaxOwnedValue()) return;
        user.transferMoney(-getCard().getModifyValue(), null);
    }

    @Override
    protected GrantCard getCard(){
        return (GrantCard)card;
    }

}
