package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.board.Square;
import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMove extends ChanceCard {
    private List<Square> validSquares; //Square-class is implemented later

    public ChanceCardMove(String description){
        super(description);
    }

    public void use(Player player) { //Player-class is implemented later

    }

}
