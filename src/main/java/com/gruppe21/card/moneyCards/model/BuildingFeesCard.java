package com.gruppe21.card.moneyCards.model;

import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class BuildingFeesCard extends ModifyMoneyCard {
    private final int hotelFee;

    public BuildingFeesCard(String descriptionOnUseLabel, int statusEffect, int houseFee, int hotelFee) {
        super(descriptionOnUseLabel, statusEffect, houseFee);
        this.hotelFee = hotelFee;
    }

    public BuildingFeesCard(Element xmlTag){
        this(xmlTag.getAttribute("onUseDescription"),
                readStatusEffect(xmlTag),
                parseInt(xmlTag.getAttribute("houseFee")),
                parseInt(xmlTag.getAttribute("hotelFee"))
        );
    }

    public int getHouseFee(){
        return getModifyValue();
    }

    public int getHotelFee() {
        return hotelFee;
    }

}
