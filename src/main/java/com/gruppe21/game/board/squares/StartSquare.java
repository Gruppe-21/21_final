package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class StartSquare extends Square {
    private int startBonus = 2;

    public StartSquare(String nameLabel, String descriptionLabel) {
        super(nameLabel, descriptionLabel);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);
        StartBonus(player);
    }

    public void StartBonus(Player player) {
        player.getBankBalance().addBalance(startBonus);
    }
}
