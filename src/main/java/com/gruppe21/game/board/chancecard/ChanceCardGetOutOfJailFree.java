package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardGetOutOfJailFree extends ChanceCard{

    public ChanceCardGetOutOfJailFree(String descriptionOnDrawLabel, String descriptionOnUseLabel){
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
    }

    public void use(Game game, Player player) {
        GUIManager.getInstance().waitForUserAcknowledgement(Localisation.getInstance().getStringValue(descriptionOnUseLabel));
    }

    @Override
    public void onDraw(Game game, Player player){
        GUIManager.getInstance().waitForUserAcknowledgement(Localisation.getInstance().getStringValue(descriptionOnDrawLabel));
    }



}
