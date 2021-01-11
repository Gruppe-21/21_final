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
        playerController.teleportTo(square[30]); //Skal ændres til fængselsquares index (id = 31, arrayindex = 30) i stedet for en squarecontroller


        return statuseffects;
    }

}
