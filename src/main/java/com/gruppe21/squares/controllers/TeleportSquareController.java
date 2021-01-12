package com.gruppe21.squares.controllers;

import com.gruppe21.game.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.player.StatusEffects;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.models.PropertySquare;
import com.gruppe21.squares.models.Square;
import com.gruppe21.squares.views.SquareView;
import org.w3c.dom.Element;

import static java.lang.Integer.parseInt;

public class TeleportSquareController extends SquareController {

    Square model;
    SquareView view;

    public TeleportSquareController(Square model, SquareView view) {
        super(model, view);
        this.model = model;
        this.view = view;
    }


    public void onMoveTo(PlayerController playercontroller, Board board) {

        playercontroller.getStatusEffects().setImprisoned(true);
        playercontroller.teleportTo(board.getSquareControllerFromId(10)); //Forsøg på noget som måske virker

    }

    public void onMoveToRight(PlayerController playercontroller) {

        playercontroller.getStatusEffects().setImprisoned(true);
        playercontroller.teleportTo(getDestination()); //Grund ide til hvordan vi tænker det skal virke

    }


}



/*
        Brug getDestinationID() til at finde ud af hvor den skal teleportere hen, returnerer int til ID af det sted man vil hen
        setDestination() skal tage en squarecontroller hvor man skal hen
 */

















    /**
     *
     *
     * @param playercontroller
     * @param board
     */

    public void onMoveToFromBoard(PlayerController playercontroller, Board board) {

        SquareController Prison = board.getSquareControllerFromId(FindSquareControllerID("PrisonSquare", board));

        playercontroller.getStatusEffects().setImprisoned(true);
        playercontroller.teleportTo(Prison);

    }

    /**
     *
     * Finds the right SquareControllerID
     *
     * Gets the board Square[] finds the name of each Square, and then finds the desired Square
     */
    public int FindSquareControllerID (String SquareName, Board board){
        SquareController[] squareControllers = board.getSquareControllers();

        int SquareControllerID = getId();                                                               // Mangler mere arbejde, men det her er ideen

        String[] Name;                                                                                  // Skal indeholde alle navnene på Squares og sammenlignes med den ønskede square




        return SquareControllerID;
    }


}


    /**
     *
     *  Resten virker ikke endnu, men der arbejdes på sagen
     *
     * */


    /*
    public void onMoveToFromXML(PlayerController playercontroller, Board board){

        playercontroller.getStatusEffects().setImprisoned(true);
        playercontroller.teleportTo(board.getSquareControllerFromId(PrisonID("PrisonSquare"))); //Skal have et tag somehow

    }

    private String ElementTag(Element tag){ //Skal have den omvendte af den her
        String elementName = tag.getNodeName();

        return  elementName;
    }


    private int PrisonID(String SquareName) {   //Skal somehow ud fra SquareName finde PrisonID
        int prisonID;
        Element tag = null;
        String elementName = tag.getNodeName();

        prisonID = !tag.getAttribute("ID").equals("") ? parseInt(tag.getAttribute("ID")) : 0; //Skal fungere omvendt så den får en String og returnerer et tag

        return prisonID;
    }
}

//Skal have fat i den rigtige squarecontroller ud fra ID fra XML


     */