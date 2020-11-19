package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.game.board.squares.StartSquare;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardStart extends ChanceCard {
    int money;

    public ChanceCardStart(String description) {
        super(description);
    }

    @Override
    public void use(Game game, int playerIndex) {
        startCard(game, playerIndex);
    }

    //Current player moves to StartSquare and receives money
    private void startCard(Game game, int  playerIndex) {
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();


        int startSquareIndex = 0;
        int modifyBalance = money; // +2M


        GUIManager.getInstance().waitForUserAcknowledgement(descriptionLabel);
        Square square = game.getBoard().getSquareAtIndex(startSquareIndex);
        game.movePlayer(playerIndex, square);
    }

}
