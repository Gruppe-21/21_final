package com.gruppe21.card.moneyCards.controllers;

import com.gruppe21.card.moneyCards.model.ModifyMoneyCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.game.GameController;
import com.gruppe21.player.PlayerController;

public class PlayerGiftsCardController extends MoneyCardController{

    public PlayerGiftsCardController(CardView view, ModifyMoneyCard card) {
        super(view, card);
    }

    @Override
    protected void modifyMoney(PlayerController user){
        for (PlayerController player: GameController.getInstance().getPlayerControllers()) {
            player.transferMoney(getCard().getModifyValue(), user);
        }
    }

}
