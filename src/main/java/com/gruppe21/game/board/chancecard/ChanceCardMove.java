package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.board.Board;
import com.gruppe21.game.board.Square;
import com.gruppe21.player.Player;

import java.util.List;

public class ChanceCardMove extends ChanceCard {
    private List<Square> validSquares; //Square-class is implemented later

    public ChanceCardMove(String description){
        super(description);
    }


    /**
     * Move current Player to specific Square on Board.
     * @param player
     * @param board
     * @param square
     */
    public void use(Player player, Board board, Square square) { //Player-class is implemented later
        movePlayer(player, board.getSquareAtNumber(board.getSquareIndex(square)));
    }
    //movePlayer(currentPlayer, board.getSquareAtNumber(sum)); code from Game line 105.

}
