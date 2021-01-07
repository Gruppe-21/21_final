package com.gruppe21.squares.views;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.squares.models.Square;
import com.gruppe21.utils.localisation.Localisation;

public class SquareView {
    final private GUIManager guiManager;
    final private Localisation localisation;

    public SquareView() {
        guiManager = GUIManager.getInstance();
        localisation = Localisation.getInstance();
    }

    public void landedOnMessage(Square model, Player player) {
        String name = localisation.getStringValue(model.getNameLocalisationId());
        String description = localisation.getStringValue(model.getDescriptionLocalisationId());

        guiManager.waitForUserAcknowledgement(model.getDescriptionLocalisationId());
    }


    }
