package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.views.SquareView;

public class MoneySquareController extends SquareController {

    MoneySquare model;
    SquareView view;

    public MoneySquareController(MoneySquare model, SquareView view){
        super(model, view);
        this.model = model;
        this.view = view;

    }

    public void OnMoveTo(PlayerController playercontroller){

    }

}
