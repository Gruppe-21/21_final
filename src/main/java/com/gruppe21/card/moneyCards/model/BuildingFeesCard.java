package com.gruppe21.card.moneyCards.model;

public class BuildingFeesCard extends ModifyMoneyCard {
    private final int hotelFee;

    public BuildingFeesCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int houseFee, int hotelFee) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel, houseFee);
        this.hotelFee = hotelFee;
    }

    public int getHouseFee(){
        return getModifyValue();
    }

    public int getHotelFee() {
        return hotelFee;
    }

}
