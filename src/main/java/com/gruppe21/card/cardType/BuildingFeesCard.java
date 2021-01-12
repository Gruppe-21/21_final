package com.gruppe21.card.cardType;

public class BuildingFeesCard extends Card {
    private boolean hasBuilding; //Check for house or hotel
    private int feeAmount; //

    public BuildingFeesCard(String descriptionOnDrawLabel, String descriptionOnUseLabel, boolean hasBuilding, int feeAmount) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.hasBuilding = hasBuilding; // skal laves om til at tjekke huse og hoteller
        this.feeAmount = feeAmount;
    }



}
