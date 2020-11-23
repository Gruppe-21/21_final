package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public abstract class ChanceCard {
    protected String descriptionOnDrawLabel;
    protected String descriptionOnUseLabel;

    public ChanceCard(String descriptionOnDrawLabel, String descriptionOnUseLabel) {
        this.descriptionOnDrawLabel = descriptionOnDrawLabel;
        this.descriptionOnUseLabel = descriptionOnUseLabel;
    }

    public abstract void onDraw(Game game, Player player);

    public void use(Game game, Player player) {
        player.returnCard(game.getDeck(), this);
    }

}
