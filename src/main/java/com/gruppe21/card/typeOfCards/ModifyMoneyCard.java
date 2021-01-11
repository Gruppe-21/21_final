package com.gruppe21.card.typeOfCards;

public class ModifyMoneyCard extends Card {
    private int modifyValue;
    private boolean fromBank; // tilføj label til cards.xml

    public ModifyMoneyCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, int modifyValue, boolean fromBank) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.modifyValue = modifyValue;
        this.fromBank = fromBank;
    }

    public int getModifyValue() {
        return modifyValue;
    }

    public void setModifyValue(int modifyValue) {
        this.modifyValue = modifyValue;
    }

    public boolean isFromBank() {
        return fromBank;
    }
}
