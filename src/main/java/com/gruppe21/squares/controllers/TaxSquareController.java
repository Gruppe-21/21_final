package com.gruppe21.squares.controllers;

import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.TaxSquare;
import com.gruppe21.squares.views.TaxSquareView;

public class TaxSquareController extends MoneySquareController{

    public TaxSquareController(TaxSquare model, TaxSquareView view) {
        super(model, view);
    }

    @Override
    public void onMoveTo(PlayerController playerController){
        super.onMoveTo(playerController);
    }

    @Override
    protected void modifyMoney(PlayerController playerController){
        if (((TaxSquareView)view).TaxOrFee(playerController, (TaxSquare) model)){
            playerController.transferMoney((int)(playerController.getTotalValue() * ((TaxSquare) model).getTax()), null);
        }
        else playerController.transferMoney(((TaxSquare) model).getModifyAmount(), null);

    }

}
