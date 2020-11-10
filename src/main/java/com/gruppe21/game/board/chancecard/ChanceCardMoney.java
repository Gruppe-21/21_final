package com.gruppe21.game.board.chancecard;

import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMoney extends ChanceCard{
    private int money;
    private List<Player> moneyOrigin; //Player-class is implemented later

    public ChanceCardMoney(String description){
        super(description);
    }

    public void use(Player player, List<Player> moneyOrigin){

    }

}
