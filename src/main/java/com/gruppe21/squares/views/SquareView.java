package com.gruppe21.squares.views;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.squares.models.Square;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Field;

public class SquareView {
    final protected GUIManager guiManager;
    final protected Localisation localisation;

    public SquareView() {
        guiManager = GUIManager.getInstance();
        localisation = Localisation.getInstance();
    }

    public void updateText(Square model) {
        String name = localisation.getStringValue(model.getNameLocalisationId());
        String description = localisation.getStringValue(model.getDescriptionLocalisationId());

        final GUI_Field guiField = model.getGuiField();

        guiField.setTitle(name);
        guiField.setDescription(description);

    }

    public void landedOnMessage(Square model, Player player) {
        String name = localisation.getStringValue(model.getNameLocalisationId());
        String description = localisation.getStringValue(model.getDescriptionLocalisationId());

        guiManager.waitForUserAcknowledgement(model.getDescriptionLocalisationId());
    }

    public String getName(Square model) {
        return localisation.getStringValue(model.getDescriptionLocalisationId());
    }


}
