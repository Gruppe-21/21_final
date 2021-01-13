package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.views.SquareView;

public class MoneySquareController extends SquareController{


    public MoneySquareController(MoneySquare model, SquareView view) {
        super(model, view);
    }

    @Override
    public void onMoveTo(PlayerController playerController){
        super.onMoveTo(playerController);
        modifyMoney(playerController);
    }

    private void modifyMoney(PlayerController playerController){
        playerController.transferMoney(-((MoneySquare)model).getModifyAmount(), null);
    }



}
