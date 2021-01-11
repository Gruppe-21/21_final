/*package com.gruppe21.prison;

import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;
import com.gruppe21.squares.controllers.SquareController;

public class GoToPrison {

    private StatusEffects statuseffects;
    private SquareController squareController;
    private PlayerController playerController;
    private SquareController Squarecontroller;

    public StatusEffects GoToPrison(PlayerController playercontroller, StatusEffects statusEffects, Board board){

        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(true);

        this.playerController = playercontroller;
        playerController.teleportTo(board.getSquareControllerFromId(31)); //Skal ændres så der bliver fundet et id ud fra XML filen (ID 31)


        return statuseffects;
    }

}


 */