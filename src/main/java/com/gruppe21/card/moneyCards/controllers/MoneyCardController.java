package com.gruppe21.card.moneyCards.controllers;
import com.gruppe21.card.cardControllers.controllers.CardController;
import com.gruppe21.card.view.CardView;
import com.gruppe21.card.moneyCards.model.ModifyMoneyCard;
import com.gruppe21.player.PlayerController;

public class MoneyCardController extends CardController {
    public MoneyCardController(CardView view, ModifyMoneyCard card) {
        super(view, card);
    }

    @Override
    public void use(PlayerController user) {
        super.use(user);
        modifyMoney(user);
    }

    protected void modifyMoney(PlayerController user){
        user.transferMoney(-getCard().getModifyValue(), null);
    }

    protected ModifyMoneyCard getCard(){
        return (ModifyMoneyCard)card;
    }

}
