package com.gruppe21.game.board.squares;

import com.gruppe21.player.Player;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}
