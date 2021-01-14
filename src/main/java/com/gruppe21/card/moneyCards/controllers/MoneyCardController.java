package com.gruppe21.card.moneyCards.controllers;

import com.gruppe21.card.cardControllers.controllers.CardController;
import com.gruppe21.card.cardControllers.controllers.UseOnDrawCardController;
import com.gruppe21.card.view.CardView;
import com.gruppe21.card.moneyCards.model.ModifyMoneyCard;
import com.gruppe21.game.GameController;
import com.gruppe21.player.PlayerController;

public class MoneyCardController extends UseOnDrawCardController {
    public MoneyCardController(CardView view, ModifyMoneyCard card) {
        super(view, card);
    }


    /**
     * Method that manages money transactions depending on card type
     *
     * <p> if the card is a matadorLegate card and the Player's {code:user} ?total value? is under
     * 15K, the user will receive 40K </p>
     * <p> else if card is a building fee type card, and the {code:user} owns house and or hotels. The player will get
     * a total fee depending on the amount of houses and hotels.</p>
     * <p> Otherwise the player will recieve money from bank or from the other players</p>
     * @param user Player that uses card
     */
    @Override
    public void use(PlayerController user) {
        ModifyMoneyCard mCard = (ModifyMoneyCard)card;
        super.use(user);

        if (mCard.getIsLegat() && user.getPlayer().getTotalValue() > mCard.getMinMoney()) { // (15000) Total value or cash value?
            user.transferMoney(-mCard.getModifyValue(), null); // (40000) negative number?
        } else if(isHouseAndHotelCard()){
            if(user.getPlayer().getOwnedProperties().length > 0){
                int feeHouse = mCard.getMoneyHouse() * user.getTotalNumberOfHouses();
                int feeHotel = mCard.getMoneyHotel() * user.getTotalNumberOfHotels();

                int totalFee = feeHouse + feeHotel;

                user.transferMoney(totalFee, null);
            }
        }else{
            if (mCard.isFromBank()) user.transferMoney(mCard.getModifyValue(), null);
            else {
                for (PlayerController playerController : GameController.getInstance().getPlayerControllers()) {
                    playerController.transferMoney(mCard.getModifyValue(), user);
                }
            }
        }
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }

    public boolean isHouseAndHotelCard(){
        return ((ModifyMoneyCard)card).getMoneyHouse() > 0;
    }

}
