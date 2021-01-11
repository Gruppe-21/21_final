package com.gruppe21.card.cardControllers;

import com.gruppe21.card.CardView;
import com.gruppe21.card.typeOfCards.ModifyMoneyCard;
import com.gruppe21.game.GameController;
import com.gruppe21.player.PlayerController;

public class MoneyCardController extends CardController  {
    private ModifyMoneyCard card;
    private CardView view;

    public MoneyCardController(CardView view, ModifyMoneyCard card) {
        super(view, card);
    }


    @Override
    public void onDraw(PlayerController drawer) {
        use(drawer);
    }

    /**
     * Card that transfer money to {code: user}.
     * <p> if the card is a matadorLegate card and the Player's {code:user} ?total/cash value? is under
     * 15K, the user will receive 40K </p>
     * <p> Otherwise the player will recieve money from bank or from the other players</p>
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user) {
        super.use(user); // text to view

        if (card.getDescriptionOnUseLabel().equals("matadorLegate") && user.getPlayer().getTotalValue() > 15000) { // Total value or cash value? getDesc?
            user.transferMoney(-card.getModifyValue(), user); // negative number?
        } else {
            if (card.isFromBank()) user.addBalance(-card.getModifyValue()); // negative number?
            else {
                for (PlayerController playerController : GameController.getInstance().getPlayerControllers()) {
                    playerController.transferMoney(card.getModifyValue(), user);
                }
            }
            user.getHeldCards().removeCard(this);
            getReturnDeck().returnCard(this);
        }
    }

}
