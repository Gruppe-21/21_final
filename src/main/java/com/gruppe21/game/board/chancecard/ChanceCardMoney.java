package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private boolean isPayToBank;
    private boolean isRecieveMoneyFromBank;
    private boolean isRecieveMoneyFromStart;


    public ChanceCardMoney(String description, int money, boolean isPayToBank, boolean isRecieveMoneyFromBank, boolean isRecieveMoneyFromStart) {
        super(description);
        this.money = money;
        this.isPayToBank = isPayToBank;
        this.isRecieveMoneyFromBank = isRecieveMoneyFromBank;
        this.isRecieveMoneyFromStart = isRecieveMoneyFromStart;
    }

    @Override
    public void use(Game game) {

        if(isPayToBank) {
            tooMuchCandy(game);
        } else if(isRecieveMoneyFromBank) {
            finishedHomework(game);
        } else if(isRecieveMoneyFromStart) {
            startCard(game);
        } else {
            birthday(game);
        }

    }
    //Den nuværende spiller mister 2M
    private void tooMuchCandy(Game game) {
//      int playerCurrentBankBalance = game.getPlayers()[playerIndex].getBankBalance;
//      playerCurrentBankBalance -= 2;
//      game.getPlayers()[playerIndex].getBankBalance.addBalance() = playerCurrentBankBalance;

    }

    //Den nuværende spiller modtager 2M
    private void finishedHomework(Game game) {

    }

    //Den nuværende spiller rykkes til startfeltet og modtager derfor 2M
    private void startCard(Game game) {

    }

    //Den nuværende spiller modtager 1M fra de andre spillere
    private void birthday(Game game) {

    }

}
