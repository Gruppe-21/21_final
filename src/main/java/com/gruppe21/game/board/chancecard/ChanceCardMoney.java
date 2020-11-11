package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private boolean isPayToBank;
    private boolean isReceiveMoneyFromBank;
    private boolean isReceiveMoneyFromStart;


    public ChanceCardMoney(String description, int money, boolean isPayToBank, boolean isReceiveMoneyFromBank, boolean isReceiveMoneyFromStart) {
        super(description);
        this.money = money;
        this.isPayToBank = isPayToBank;
        this.isReceiveMoneyFromBank = isReceiveMoneyFromBank;
        this.isReceiveMoneyFromStart = isReceiveMoneyFromStart;
    }

    @Override
    public void use(Game game,Player player) {

        if(isPayToBank) {
            tooMuchCandy(player);
        } else if(isReceiveMoneyFromBank) {
            finishedHomework(player);
        } else if(isReceiveMoneyFromStart) {
            startCard(game);
        } else {
            birthday(game);
        }

    }
    //Current player loses 2M
    private void tooMuchCandy(Player player) {
        int modifyBalance = money;
        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives 2M
    private void finishedHomework(Player player) {
        int modifyBalance = money;
        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player moves to StartSquare and receives 2M
    private void startCard(Player player) {


    }

    //Current player receives 1M from other players
    private void birthday(Game game) {

    }

}
