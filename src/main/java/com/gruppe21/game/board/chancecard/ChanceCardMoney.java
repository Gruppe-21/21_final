package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

public class ChanceCardMoney extends ChanceCard {
    private final int money;
    private final MoneyCardType cardType;

    public ChanceCardMoney(String description, int money, MoneyCardType cardType) {
        super(description);
        this.money = money;
        this.cardType = cardType;

    }

    @Override
    public void use(Game game,Player player) {

        switch (cardType){

            case Bank -> {
                modifyPlayerBalance(game, player);
            }
            case Birthday -> {
                receiveFromPlayers(game,  game.getPlayers(), player);
            }
        }
    }

    private void modifyPlayerBalance(Game game, Player player) {
        int modifyBalance = money; // -2M
        BankBalance playerCurrentBalance = player.getBankBalance();
        GUIManager.getInstance().waitForUserAcknowledgement(descriptionLabel);
        playerCurrentBalance.addBalance(modifyBalance);
    }
    //Current player receives money from other players
    private void receiveFromPlayers(Game game, Player[] players, Player player) {
        Player[] payingPlayersArr = new Player[players.length-1];
        int modifyBalance = money; // +1M

        GUIManager.getInstance().waitForUserAcknowledgement(descriptionLabel);

        // Filter out the player receiving money
        int i = 0,j = 0;
        while (i < players.length) {
            if(players[i] != player){
                payingPlayersArr[j] = players[i];
                j++;
            }
            i++;
        }

        for (Player payingPlayer : payingPlayersArr) {
            // paying players lose money
            BankBalance payingPlayerBalance = payingPlayer.getBankBalance();
            payingPlayerBalance.addBalance(-1 * modifyBalance); // (-1M)

            // receiving player gain money
            BankBalance receivingPlayerBalance = player.getBankBalance();
            receivingPlayerBalance.addBalance(modifyBalance);// (+1M)
        }
    }
}
