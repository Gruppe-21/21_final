package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class ChanceCardGetOutOfJailFree extends ChanceCard{

    public ChanceCardGetOutOfJailFree(String description){
        super(description);
    }

    @Override
    public void use(Game game, Player player) {
        super.use(game, player);
        player.prisonStatus = false;
    }



}
