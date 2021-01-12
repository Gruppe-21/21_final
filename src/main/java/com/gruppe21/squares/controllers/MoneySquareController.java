package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;

public class MoneySquareController extends SquareController {

    MoneySquare model;
    SquareView view;
    PlayerController PlayerController;

    public Test(MoneySquare model, SquareView view){
        super(model, view);
        this.model = model;
        this.view = view;


    }

    public MoneySquareController(Square model, SquareView view) {
        super(model, view);
    }

    public void OnMoveTo(PlayerController playercontroller){
        this.PlayerController = playercontroller;

        PlayerController.transferMoney(-10000, null);
    }

}
