package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardMoney extends ChanceCard {
    private final int money;
    private final MoneyCardType cardType;

    public ChanceCardMoney(String descriptionLabel, int money, MoneyCardType cardType) {
        super(descriptionLabel);
        this.money = money;
        this.cardType = cardType;

    }

    @Override
    public void onDraw(Game game, Player player){
        use(game, player);
    }



    @Override
    public void use(Game game,Player player) {
        super.use(game, player);
        Localisation localisation = Localisation.getInstance();

        GUIManager.getInstance().waitForUserAcknowledgement(
                localisation.getStringValue(
                        descriptionLabel,
                        localisation.getStringValue("currencyPrefix"),
                        Integer.toString(money),
                        localisation.getStringValue("currencySuffix")
                ));

        switch (cardType){
            case Bank -> player.getBankBalance().addBalance(money);
            case Birthday -> {
                for (Player debtor : game.getPlayers()) {
                    //If player == debtor nothing changes.
                    debtor.getBankBalance().transferMoney(money, player);
                }
            }
        }

        /*
        Player[] debtors = cardType == MoneyCardType.Bank ? null : game.getPlayers();
        if (debtors == null){
            player.getBankBalance().addBalance(money);
        }
        else {
            for (Player debtor : debtors) {
                player.getBankBalance().addBalance(money);
                debtor.getBankBalance().addBalance(-money);
            }
        }
         */


        //old
        /*
        switch (cardType){

            case Bank -> {
                modifyPlayerBalance(game, player);
            }
            case Birthday -> {
                receiveFromPlayers(game,  game.getPlayers(), player);
            }
        }
        */

    }

/*
    private void modifyPlayerBalance(Game game, Player player) {
        int modifyBalance = money; // -2M
        BankBalance playerCurrentBalance = player.getBankBalance();
        playerCurrentBalance.addBalance(modifyBalance);
    }
    //Current player receives money from other players
    private void receiveFromPlayers(Game game, Player[] players, Player player) {
        Player[] payingPlayersArr = new Player[players.length-1];
        int modifyBalance = money; // +1M

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

 */


}
