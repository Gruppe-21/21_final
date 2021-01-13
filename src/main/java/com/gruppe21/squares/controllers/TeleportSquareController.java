package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.TeleportSquare;
import com.gruppe21.squares.views.SquareView;

public class TeleportSquareController extends SquareController {

    public TeleportSquareController(TeleportSquare model, SquareView view) {
        super(model, view);
    }

    public void onMoveTo(PlayerController playerController){
        super.onMoveTo(playerController);
        playerController.teleportTo(((TeleportSquare)model).getDestinationController());
    }

    public void setDestinationController(SquareController squareController){
        ((TeleportSquare)model).setDestinationController(squareController);
    }

    public int getDestinationId(){
        return ((TeleportSquare)model).getDestinationId();
    }

}
