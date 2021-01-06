package com.gruppe21.squares.controllers;

import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class SquareController {
    final private Square model;
    final private SquareView view;

    public SquareController(Square model, SquareView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView(){
        // Update stuff inside of view object
    }

    public void onMoveTo(PlayerController playerController){
        // handle when player has moved to


        // Pass data on to view
        view.landedOnMessage(Player player);
    }


}


