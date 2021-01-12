package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.CardSquare;
import com.gruppe21.squares.models.CardSquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class CardSquareController extends SquareController {
    public CardSquareController(Square model, SquareView view) {
        super(model, view);
        updateView();

    }

    @Override
    public void onMoveTo(PlayerController playerController) {
        super.onMoveTo(playerController);
        playerController.drawCard(((CardSquare)model).getDeck());
    }
}
}
