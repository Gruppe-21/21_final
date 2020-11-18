package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class PrisonSquare extends Square {

    public PrisonSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);


    }
}