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

    public void getOutOfJail(Game game, Player player){
        player.prisonStatus = false;
        GUIManager guiManager = GUIManager.getInstance();
        Localisation localisation = Localisation.getInstance();
        //If a "get out of jail free" card is available, it is used.
        //Todo: the player should probably be told
        for (ChanceCard cCard : player.getOwnedCards().toArray(new ChanceCard[0])) {
            if (cCard.getClass() == ChanceCardGetOutOfJailFree.class){
                cCard.use(game, player);
                return;
            }
        }
        //If such a card is not available, then the player will have to pay.
        guiManager.waitForUserAcknowledgement(localisation.getStringValue(paidReleaseLabel, Integer.toString(price)));
        player.getBankBalance().addBalance(price);
    }

}