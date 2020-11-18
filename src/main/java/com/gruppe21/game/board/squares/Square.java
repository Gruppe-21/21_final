package com.gruppe21.game.board.squares;

import com.gruppe21.game.board.Board;
import com.gruppe21.player.Player;

/*
        Opretter en basic Square
 */

public abstract class Square {
    private String name;
    private String description;
    private Board board;

    public Square(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void handleLandOn(Player player) {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}




/*
        Creates a FreeParking square class:
 */


/*
        Creates a GoToPrisonSquare class:
 */


/*
        Creates a ChanceSquare class:
 */


/*
        Creates a PropertySquare class:
 */

