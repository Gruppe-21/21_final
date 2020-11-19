package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardStart extends ChanceCard {
    public ChanceCardStart(String description) {
        super(description);
    }

    @Override
    public void use(Game game, Player player) {
        startCard(game, player);
    }

    //Current player moves to StartSquare and receives money
    private void startCard(Game game, Player player) {
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();


        guiManager.waitForUserAcknowledgement(localisation.getStringValue(descriptionLabel));
        int startSquareIndex = 0;
        Square square = game.getBoard().getSquareAtIndex(startSquareIndex);
        game.movePlayer(player, square);
    }

}
