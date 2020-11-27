package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class FreeParkingSquare extends Square {

    public FreeParkingSquare(String nameLabel, String descriptionLabel) {
        super(nameLabel, descriptionLabel);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);
    }
}
