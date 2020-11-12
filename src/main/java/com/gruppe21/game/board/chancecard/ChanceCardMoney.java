package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private boolean isPayToBank;
    private boolean isReceiveMoneyFromBank;
    private boolean isBirthday;

    public ChanceCardMoney(String description, int money, boolean isPayToBank, boolean isReceiveMoneyFromBank, boolean isBirthday) {
        super(description);
        this.money = money;
        this.isPayToBank = isPayToBank;
        this.isReceiveMoneyFromBank = isReceiveMoneyFromBank;
        this.isBirthday = isBirthday;
    }

    @Override
    public void use(Game game,Player player) {

        if(isPayToBank) {
            tooMuchCandy(player);
        } else if(isReceiveMoneyFromBank) {
            finishedHomework(player);
        }
    }

    @Override
    public void use(Game game,Player[] players, Player player){
        if(isBirthday)
            birthday(game,players,player);
    }


    //Current player loses money
    private void tooMuchCandy(Player player) {
        int modifyBalance = money; // -2M
        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives money
    private void finishedHomework(Player player) {
        int modifyBalance = money; // +2M
        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives money from other players
    private void birthday(Game game,Player[] players, Player player) {
        Player[] payingPlayersArr = new Player[players.length-1];
        int modifyBalance = money; // +1M

        game.getGuiWrapper().showMessage(description);

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
