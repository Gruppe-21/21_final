package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private boolean isPayToBank;
    private boolean isReceiveMoneyFromBank;
    private boolean isMoveToStart;
    private boolean isBirthday;


    public ChanceCardMoney(String description, int money, boolean isPayToBank, boolean isReceiveMoneyFromBank,boolean isMoveToStart, boolean isBirthday) {
        super(description);
        this.money = money;
        this.isPayToBank = isPayToBank;
        this.isReceiveMoneyFromBank = isReceiveMoneyFromBank;
        this.isMoveToStart = isMoveToStart;
        this.isBirthday = isBirthday;
    }

    @Override
    public void use(Game game,Player player) {

        if(isPayToBank) {
            tooMuchCandy(player);
        } else if(isReceiveMoneyFromBank) {
            finishedHomework(player);
        } else {
            birthday(game);
        }

    }

    @Override
    public void use(Game game,Player player,int playerIndex){
        if(isMoveToStart)
            startCard(game,player,playerIndex);
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
    private void startCard(Game game,Player player, int playerIndex) {
        int startSquareIndex = 1; // game.getBoard().getSquareAtNumber(moveToSquare);
        int modifyBalance = money; // 2M

        game.getGuiWrapper().showMessage(description);
        Square square = game.getBoard().getSquareAtNumber(startSquareIndex);
        game.movePlayer(playerIndex, square);

        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives 1M from other players
    private void birthday(Game game) {

    }

}
