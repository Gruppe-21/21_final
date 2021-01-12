package com.gruppe21.squares.controllers;

import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class TeleportSquareController extends SquareController {

    Square model;
    SquareView view;

    public TeleportSquareController(Square model, SquareView view){
        super(model,view);
        this.model = model;
        this.view = view;
    }



    public void onMoveTo(PlayerController playercontroller, Board board){

        playercontroller.getStatusEffects().setImprisoned(true);
        playercontroller.teleportTo(board.getSquareControllerFromId(PrisonID(MoneySquare))); //Skal ændres så der bliver fundet et id ud fra XML filen (ID 31)

    }


    private int PrisonID(Element tag) {

        int prisonID = new int;
        prisonID= !tag.getAttribute("ID").equals("") ? parseInt(tag.getAttribute("ID")) : 0;

        return prisonID;
    }
}

//Skal have fat i den rigtige squarecontroller ud fra ID fra XML