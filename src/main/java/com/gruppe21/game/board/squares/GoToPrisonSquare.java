package com.gruppe21.game.board.squares;
import com.gruppe21.player.Player;

public class GoToPrisonSquare extends Square {

    public GoToPrisonSquare(String name, String description) {
        super(name, description);

        @Override
        public void handleLandOn(Player player) {
            super.handleLandOn(player);

        }

    }
}