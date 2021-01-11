package com.gruppe21.card.typeOfCards;

import com.gruppe21.card.cardControllers.CardController;

public class ModifyMoneyCard extends Card {
    private int modifyValue;
    private boolean isBank;
    private boolean isLegat;
    private int minMoney;
    private int moneyHouse;
    private int moneyHotel;

    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue, boolean fromBank) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
        this.isBank = fromBank;
    }

    // House/Hotel
    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, boolean fromBank, int moneyHouse, int moneyHotel) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
        this.isBank = fromBank;
        this.moneyHouse = moneyHouse;
        this.moneyHotel = moneyHotel;
    }

    // Used in matadorLegat
    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue, boolean isBank,boolean isLegat,int minMoney) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
        this.isBank = isBank;
        this.isLegat = isLegat;
        this.minMoney = minMoney;
    }


    public int getModifyValue() {
        return modifyValue;
    }

    public void setModifyValue(int modifyValue) {
        this.modifyValue = modifyValue;
    }

    public boolean isFromBank() {
        return isBank;
    }

    public boolean getIsLegat(){
        return isLegat;
    }

    public int getMinMoney(){
        return minMoney;
    }

    public int getMoneyHouse(){
        return moneyHouse;
    }

    public int getMoneyHotel(){
        return moneyHotel;
    }

}
