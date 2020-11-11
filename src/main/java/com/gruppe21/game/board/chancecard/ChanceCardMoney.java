package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;

import java.util.Arrays;

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
        }
    }

    @Override
    public void use(Game game,Player player,int playerIndex){
        if(isMoveToStart)
            startCard(game,player,playerIndex);
    }

    @Override
    public void use(Player[] players, Player player){
        if(isBirthday)
            birthday(players,player);
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
    private void startCard(Game game,Player player,int playerIndex) {
        int startSquareIndex = 1; // game.getBoard().getSquareAtNumber(moveToSquare);
        int modifyBalance = money; // 2M

        game.getGuiWrapper().showMessage(description);
        Square square = game.getBoard().getSquareAtNumber(startSquareIndex);
        game.movePlayer(playerIndex, square);

        BankBalance playerCurrentBalance = player.getBankBalance();

        playerCurrentBalance.addBalance(modifyBalance);
    }

    //Current player receives 1M from other players
    private void birthday(Player[] players, Player player) {
        Player[] payingPlayersArr = new Player[players.length-1];
        int payingPlayersCounter = 0;
        int modifyBalance = money;

        // Filter out payingPlayers
        for (int i = 0,j = 0; i < players.length; i++) {
            if(players[i] != player){
                payingPlayersArr[j] = players[i];
                j++;
            }
        }

        for (int i = 0; i <= payingPlayersCounter; i++) {
            // paying players lose money (1M)
            BankBalance payingPlayerBalance = payingPlayersArr[i].getBankBalance();
            payingPlayersArr[i].getBankBalance().addBalance(modifyBalance);
            payingPlayerBalance.addBalance(modifyBalance);

            // receiving player gain money (1M)
            BankBalance receivingPlayerBalance = player.getBankBalance();
            player.getBankBalance().addBalance(modifyBalance);
            receivingPlayerBalance.addBalance(modifyBalance);
        }

    }
}

