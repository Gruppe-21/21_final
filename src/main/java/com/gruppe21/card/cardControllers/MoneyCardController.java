package com.gruppe21.card.cardControllers;

import com.gruppe21.card.cardView.CardView;
import com.gruppe21.card.cardType.ModifyMoneyCard;
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
        if (card == null || cardView == null) return; //This should never happen
        super.use(user);

        if (card.getIsLegat() && user.getPlayer().getTotalValue() > card.getMinMoney()) { // (15000) Total value or cash value?
            user.transferMoney(-card.getModifyValue(), null); // (40000) negative number?
        } else if(isHouseAndHotelCard()){
            if(user.getPlayer().getOwnedProperties().length > 0){
                int totalFee = 0;

                // Calculate price for house ??
                // totalFee += card.getMoneyHouse() * user.getPlayer().getNumberOfHouses;


                // Calculate price for hotel ??
                // totalFee += card.getMoneyHotel() * user.getPlayer().getNumberOfHotels;

                user.transferMoney(totalFee, user);
            }
        }else{
            if (card.isFromBank()) user.addBalance(-card.getModifyValue()); // negative number?
            else {
                for (PlayerController playerController : GameController.getInstance().getPlayerControllers()) {
                    playerController.transferMoney(card.getModifyValue(), user);
                }
            }
        }
        user.getHeldCards().removeCard(this);
        getReturnDeck().returnCard(this);
    }

    public boolean isHouseAndHotelCard(){
        return this.card.getMoneyHouse() > 0;
    }

}
