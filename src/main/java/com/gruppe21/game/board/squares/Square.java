package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Board;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

/*
        Opretter en basic Square
 */

public abstract class Square {
    private String nameLabel;
    private String descriptionLabel;
    protected Board board;

    public Square(String nameLabel, String descriptionLabel) {
        this.nameLabel = nameLabel;
        this.descriptionLabel = descriptionLabel;
    }

    public void handleLandOn(Player player) {
        String text = Localisation.getInstance().getStringValue(descriptionLabel);
        GUIManager.getInstance().waitForUserAcknowledgement(text);
    }

    public void handleLandOn(Player player, Game game) {
        handleLandOn(player);
    }

    public String getDescription() {
        return  Localisation.getInstance().getStringValue(descriptionLabel);
    }

    public void setDescription(String description) {
        this.descriptionLabel = description;
    }

    public String getNameLabel() {
        return Localisation.getInstance().getStringValue(nameLabel) ;
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel = nameLabel;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getLabel() {return nameLabel;}
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

