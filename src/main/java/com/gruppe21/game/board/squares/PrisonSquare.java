package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.chancecard.ChanceCard;
import com.gruppe21.game.board.chancecard.ChanceCardGetOutOfJailFree;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class PrisonSquare extends Square {
    private final int price;
    private String paidReleaseLabel;

    public PrisonSquare(String nameLabel, String descriptionLabel, String paidReleaseLabel, int price) {
        super(nameLabel, descriptionLabel);
        this.price = price;
        this.paidReleaseLabel = paidReleaseLabel;
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);
    }

    public void getOutOfJail(Player player){
        player.prisonStatus = false;
        //If a "get out of jail free" card is available, it is used.
        for (ChanceCard cCard : player.getOwnedCards()) {
            if (cCard.getClass() == ChanceCardGetOutOfJailFree.class){
                cCard.use();
                return;
            }
        }
        //If such a card is not available, then the player will have to pay.
        GUIManager guiManager = GUIManager.getInstance();
        Localisation localisation = Localisation.getInstance();
        guiManager.waitForUserAcknowledgement(localisation.getStringValue(paidReleaseLabel, Integer.toString(price)));
        player.getBankBalance().addBalance(price);
    }

}