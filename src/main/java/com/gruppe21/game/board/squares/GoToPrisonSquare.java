package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

public class GoToPrisonSquare extends Square {

    public GoToPrisonSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player, Game game) {

        Square gotoprisonSquare = null;

        for (Square square : board.getSquares().toArray()) {
            if(square.getClass() ==  GoToPrisonSquare.class){
                gotoprisonSquare = square;
                break;
            }
        }

        if(gotoprisonSquare != null){
            game.teleportPlayer(player, gotoprisonSquare);
            player.prisonStatus = true;
        }

    }

}