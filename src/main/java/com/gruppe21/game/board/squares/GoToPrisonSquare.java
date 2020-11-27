package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Board;
import com.gruppe21.player.Player;

public class GoToPrisonSquare extends Square {

    public GoToPrisonSquare(String nameLabel, String descriptionLabel) {
        super(nameLabel, descriptionLabel);
    }

    @Override
    public void handleLandOn(Player player, Game game) {
        super.handleLandOn(player, game);

        Board board = game.getBoard();
        game.teleportPlayer(player, board.getSquareClosetToSquare(this, board.getSquaresOfClass(PrisonSquare.class)));
        player.prisonStatus = true;
    }
}