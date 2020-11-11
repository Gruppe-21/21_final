package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMoney extends ChanceCard {
    private int money;
    private List<Player> moneyOrigin; //Player-class is implemented later

    public ChanceCardMoney(String description, int money) {
        super(description);
        this.money = money;
    }

    @Override
    public void use(Game game) {
        super.use(game);
    }


    public void use(Player player, List<Player> moneyOrigin) {

    }

}
