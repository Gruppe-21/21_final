package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public abstract class ChanceCard {
    protected String description;

    public ChanceCard(String description) {
        this.description = description;
    }

    public void use(Game game,int playerIndex) {
        // Do something
    }

    public void use(Game game, Player player) {
        // Do something
    }
}
