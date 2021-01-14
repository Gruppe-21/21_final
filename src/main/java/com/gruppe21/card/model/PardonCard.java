package com.gruppe21.card.model;

import org.w3c.dom.Element;


public class PardonCard extends Card{
    protected final String descriptionOnDrawLabel;
    public PardonCard(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        super(descriptionOnUseLabel);
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
    }

    public PardonCard(Element xmlTag){
        this(xmlTag.getAttribute("onDrawDescription"),
                xmlTag.getAttribute("onUseDescription")
        );
    }

    public String getDescriptionOnDrawLabel() {
        return descriptionOnDrawLabel;
    }
}
