package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.Square;
import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMove extends ChanceCard {
    private List<Square> validSquares; //Square-class is implemented later
    private int moveToSquare;

    public ChanceCardMove(String description, int moveToSquare){
        super(description);
        this.moveToSquare = moveToSquare;
    }


    /**
     * Move current Player to specific Square on Board.
     * @param currentPlayer
     */
     //To-Do find out how to move player.
    public void use(Player player) { //Player-class is implemented later
        movePlayer(player, moveToSquare);
    }
    

}
