package com.gruppe21.card.moneyCards.controllers;

import com.gruppe21.card.moneyCards.model.BuildingFeesCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.player.PlayerController;

public class BuildingFeesCardController extends MoneyCardController {

    public BuildingFeesCardController(CardView view, BuildingFeesCard card) {
        super(view, card);
    }

    @Override
    protected void modifyMoney(PlayerController user){
        int totalFee = user.getTotalNumberOfHouses() * getCard().getHouseFee() + user.getTotalNumberOfHotels() + getCard().getHotelFee();
        user.transferMoney(totalFee, null);
    }

    @Override
    protected BuildingFeesCard getCard(){
        return (BuildingFeesCard)card;
    }
}
