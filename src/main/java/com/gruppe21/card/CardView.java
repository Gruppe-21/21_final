package com.gruppe21.card;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.utils.localisation.Localisation;

public class CardView {
    public void displayCard(String messageLabel, String... variables){
        GUIManager.getInstance().displayCard(Localisation.getInstance().getStringValue(messageLabel, variables));
    }
}
