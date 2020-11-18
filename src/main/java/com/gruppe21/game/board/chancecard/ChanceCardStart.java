package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardStart extends ChanceCard {
    int money;

    public ChanceCardStart(String description, int money) {
        super(description);
        this.money = money;
    }

    @Override
    public void use(Game game, Player player) {
        startCard(game, player);
    }

    //Current player moves to StartSquare and receives money
    private void startCard(Game game, Player player) {
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();

        int playerIndex = game.getCurrentPlayer();
        int startSquareIndex = 1; // TO-DO: game.getBoard().getSquareAtNumber(start)
        int modifyBalance = money; // +2M


        game.getGuiWrapper().showMessage(descriptionLabel);
        Square square = game.getBoard().getSquareAtIndex(startSquareIndex);
        game.movePlayer(playerIndex, square);

        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

}
