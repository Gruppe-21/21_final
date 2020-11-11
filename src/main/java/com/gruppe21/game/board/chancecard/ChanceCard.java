package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;

public abstract class ChanceCard {
    protected String description;

    public ChanceCard(String description) {
        this.description = description;
    }

    public void use(Game game) {
        // Do something
    }
}
