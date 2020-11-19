package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Deck.Deck;
import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceSquare extends Square {

    public ChanceSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);

        String takeCardMessage = Localisation.getInstance().getStringValue("takecard");
        GUIManager.getInstance().waitForUserButtonPress(takeCardMessage, "✓");
        Deck deck = game.getDeck();
        ChanceCard card = deck.drawCard(null);
    }

}
