package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

public class GoToPrisonSquare extends Square {

    public GoToPrisonSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player, Game game) {

        Square prisonSquare = null;

        for (Square square : board.getSquares().toArray(new Square[0])) {
            if(square.getClass() ==  GoToPrisonSquare.class){
                prisonSquare = square;
                break;
            }
        }

        if(prisonSquare != null){
            game.teleportPlayer(player, prisonSquare);
            player.prisonStatus = true;

        }

    }

}