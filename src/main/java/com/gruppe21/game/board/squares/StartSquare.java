package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.text.Format;

public class StartSquare extends Square {
    private int startBonus = 2;

    public StartSquare(String name, String description) {
        super(name, description);
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
