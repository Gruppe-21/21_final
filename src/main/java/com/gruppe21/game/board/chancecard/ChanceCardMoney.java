package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;

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
    public void use(Game game) {

        if(isPayToBank) {
            tooMuchCandy(game);
        } else if(isReceiveMoneyFromBank) {
            finishedHomework(game);
        } else if(isReceiveMoneyFromStart) {
            startCard(game);
        } else {
            birthday(game);
        }

    }
    //Current player loses 2M
    private void tooMuchCandy(Game game) {
        int playerIndex = game.getCurrentPlayer();
        int playerCurrentBalance = game.getPlayers()[playerIndex].getBankBalance().getBalance();
        game.getPlayers()[playerIndex].getBankBalance().addBalance(money);

        if(playerCurrentBalance<=0) {
            // Spiller er gået fallit...hvad så?
            // Vil tro koden kommer til at se sådan ud:
            // return false; // !playround Game
        }
    }

    //Current player receives 2M
    private void finishedHomework(Game game) {

    }

    //Current player moves to StartSquare and receives 2M
    private void startCard(Game game) {

    }

    //Current player receives 1M from other players
    private void birthday(Game game) {

    }

}
