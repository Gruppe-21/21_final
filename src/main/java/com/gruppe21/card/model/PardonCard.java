package com.gruppe21.card.model;

public class PardonCard extends Card{
    protected final String descriptionOnDrawLabel;
    public PardonCard(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        super(descriptionOnUseLabel);
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
    }

    public String getDescriptionOnDrawLabel() {
        return descriptionOnDrawLabel;
    }
}
