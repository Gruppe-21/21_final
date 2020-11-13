package com.gruppe21.game.board.squares;
import com.gruppe21.player.Player;

import java.awt.Color;

/*
        Opretter en basic Square
 */

public class Square {
    private String name;
    private String description;

    public Square(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void handleLandOn(Player player){
        // Lav metoden færdig
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

