package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.ModifyMoneyCard;
import com.gruppe21.game.GameController;
import com.gruppe21.player.PlayerController;

public class MoneyCardController extends CardController  {
    private ModifyMoneyCard card;

    public MoneyCardController(CardView view, ModifyMoneyCard card) {
        super(view, card);
    }

    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    @Override
    public void use(PlayerController user){
        super.use(user); // text to view
        if (card.isFromBank()) user.addBalance(card.getModifyValue());
        else {
            for (PlayerController playerController: GameController.getInstance().getPlayerControllers()) {
                playerController.transferMoney(card.getModifyValue(), user);
            }
        }
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }

}
