package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class PropertySquareController extends SquareController {
    public PropertySquareController(Square model, SquareView view) {
        super(model, view);
    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
    }
}
