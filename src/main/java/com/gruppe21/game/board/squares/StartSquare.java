package com.gruppe21.game.board.squares;

import com.gruppe21.player.Player;

public class StartSquare extends Square {
    private int startBonus = 2;

    public StartSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}
