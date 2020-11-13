package com.gruppe21.game.board.squares;

import com.gruppe21.player.Player;

public class ChanceSquare extends Square {

    public ChanceSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }

    public ChangeCard drawCard() {
        // Fix kode her
    }
}
