package com.gruppe21.squares.controllers;

import com.gruppe21.player.Player;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;
import gui_fields.GUI_Field;

/**
 *
 */
public class SquareController {
    final protected Square model;
    final protected SquareView view;

    public SquareController(Square model, SquareView view) {
        this.model = model;
        this.view = view;
        updateView();
    }

    public void updateView(){
       view.updateText(model);
    }

    public void onMoveTo(PlayerController playerController){
        // handle when player has moved to

        // Pass data on to view
        //updateView();
        view.landedOnMessage(model, playerController.getPlayer());
        playerController.getStatusEffects().enableEffect(model.getStatusEffect());
    }

    public GUI_Field getSquareField(){return model.getGuiField();}

    public String getName(){
        return view.getName(model);
    }

    public SquareView getView(){ return view; }


    public int getId() {
        return model.getId();
    }


}


