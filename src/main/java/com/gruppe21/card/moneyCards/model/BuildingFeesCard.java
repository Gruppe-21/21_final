package com.gruppe21.card.moneyCards.model;

import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class BuildingFeesCard extends ModifyMoneyCard {
    private final int hotelFee;

    public BuildingFeesCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int houseFee, int hotelFee) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel, houseFee);
        this.hotelFee = hotelFee;
    }

    public BuildingFeesCard(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"),
                xmlTag.getAttribute("onUseDescription"),
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
