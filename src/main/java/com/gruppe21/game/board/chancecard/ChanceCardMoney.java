package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private boolean isPayToBank;
    private boolean isHomework;
    private boolean isBirthday;

    public ChanceCardMoney(String description, int money, boolean isPayToBank, boolean isReceiveMoneyFromBank, boolean isBirthday) {
        super(description);
        this.money = money;
        this.isPayToBank = isPayToBank;
        this.isHomework = isReceiveMoneyFromBank;
        this.isBirthday = isBirthday;
    }

    @Override
    public void use(Game game,Player player) {
        if(isPayToBank) {
            tooMuchCandy(game, player);
        } else if(isHomework) {
            finishedHomework(game,player);  // isReceivemoneyFromBank
        }else{
            birthday(game,  game.getPlayers(), player);
        }

    }




    //Current player loses money
    private void tooMuchCandy(Game game, Player player) {
        int modifyBalance = money; // -2M
        BankBalance playerCurrentBalance = player.getBankBalance();

        GUIManager.getInstance().waitForUserAcknowledgement(descriptionLabel);
        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives money
    private void finishedHomework(Game game,Player player) {
        int modifyBalance = money; // +2M
        BankBalance playerCurrentBalance = player.getBankBalance();

        GUIManager.getInstance().waitForUserAcknowledgement(descriptionLabel);
        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives money from other players
    private void birthday(Game game,Player[] players, Player player) {
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
