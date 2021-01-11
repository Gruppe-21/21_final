package com.gruppe21.prison;

import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;
import com.gruppe21.squares.controllers.SquareController;

public class GoToPrison {

    private StatusEffects statuseffects;
    private SquareController squareController;
    private PlayerController playerController;
    private SquareController Squarecontroller;

    public StatusEffects GoToPrison(StatusEffects statusEffects, PlayerController playercontroller){

        this.statuseffects = statusEffects;
        statuseffects.setImprisoned(true);

        this.playerController = playercontroller;
        playerController.teleportTo(Board.square[30]); //Skal ændres så der bliver fundet et id ud fra XML filen


        return statuseffects;
    }

}
