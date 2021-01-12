package com.gruppe21.card.cardType;

public class ModifyMoneyCard extends Card {
    private int modifyValue;
    private boolean isBank;
    private boolean isLegate;
    private int minMoney;
    private int moneyHouse;
    private int moneyHotel;

    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue, boolean fromBank) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
        this.isBank = fromBank;
    }

    // Used in building fee cards (House/Hotel)
    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, boolean fromBank, int moneyHouse, int moneyHotel) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.isBank = fromBank;
        this.moneyHouse = moneyHouse;
        this.moneyHotel = moneyHotel;
    }

    // Used in matadorLegat card
    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int legateMoney, boolean legateIsBank, boolean isLegate,int minMoney) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = legateMoney;
        this.isBank = legateIsBank;
        this.isLegate = isLegate;
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
        return isLegate;
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
