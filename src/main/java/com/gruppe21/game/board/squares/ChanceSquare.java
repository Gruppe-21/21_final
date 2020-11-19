package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class ChanceSquare extends Square {

    public ChanceSquare(String name, String descriptionLabel) {
        super(name, descriptionLabel);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);

        //String takeCardMessage = Localisation.getInstance().getStringValue("takecard");
        //GUIManager.getInstance().waitForUserButtonPress(takeCardMessage, "✓");
        player.drawChanceCard(game.getDeck(), game);
    }

}
