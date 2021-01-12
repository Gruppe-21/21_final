package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;
import com.sun.tools.javac.comp.Todo;

public class MoneySquareController extends SquareController {

    MoneySquare model;
    SquareView view;

    public MoneySquareController(MoneySquare model, SquareView view){
        super(model, view);
        this.model = model;
        this.view = view;


    }

    public MoneySquareController(Square model, SquareView view) {
        super(model, view);
    }
    /*
    *   Skal laves om til at læse fra XML filen og finde den rigtige værdi der
    *
    * */
    public void OnMoveTo(PlayerController playercontroller){

        playercontroller.transferMoney(-4000, null); //ID 5 (4000) og ID 39 (2000)
    }

}
