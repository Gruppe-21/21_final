package com.gruppe21.squares.controllers;

import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class TeleportSquareController extends SquareController {

    private StatusEffects statuseffects;


    public TeleportSquareController(Square model, SquareView view){
        super(model,view);
    }


    public void onMoveTo(PlayerController playercontroller, Board board){

        /*
        this.statuseffects = playercontroller.getStatusEffects();
        statuseffects.setImprisoned(true);

         */

        playercontroller.getStatusEffects().setImprisoned(true);


        playercontroller.teleportTo(board.getSquareControllerFromId(31)); //Skal ændres så der bliver fundet et id ud fra XML filen (ID 31)

    }
}
