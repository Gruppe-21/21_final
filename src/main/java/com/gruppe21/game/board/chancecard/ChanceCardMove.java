package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.utils.ColorUtil;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

enum MoveCardType{
    MoveToSquare,
    MoveUpTo,
    Figure,
    TakeOrMove,
    FreeSquare
}

public class ChanceCardMove extends ChanceCard {

    private MoveCardType cardType;
    private String label;
    private Color color;

    public ChanceCardMove(String description, MoveCardType cardType, String label, String color) {
        super(description);
        this.cardType = cardType;
        this.label = label;
        this.color = ColorUtil.getColor(color);
    }




    @Override
    public void use(Game game, int playerIndex) {

        switch (cardType){
            case MoveToSquare -> {
                move(game,playerIndex);
            }
            case MoveUpTo -> {
                moveUpTo(game,playerIndex);
            }
            case Figure -> {
                giveCardToFigure(game,playerIndex);
            }
            case TakeOrMove -> {
                takeCard(game,playerIndex);
            }
            case FreeSquare -> {
                freeColorSquare(game,playerIndex);
            }
        }
    }


    private void freeColorSquare(Game game,int playerIndex){
//        Player[] playerProperty = game.getPlayers()[playerIndex].getPropertyOwned();
//        Square square = game.getBoard().getSquareAtNumber(moveToSquare);
//        // moveToSquare = next free color x;
//
//        this.move(game); // move to square with color x
//
//        if(square != playerProperty){  // not possible yet
//            // Pay rent to owner of property
//        }else{
//            // Get property for free
//        }
//
    }

    private void takeCard(Game game,int playerIndex) {
        // int playerIndex = game.getCurrentPlayer();
        int playerCurrentSquareIndex = game.getPlayers()[playerIndex].getCurrentSquareIndex();
        int moveToSquare;
        String moveButton = Localisation.getInstance().getStringValue("moveButton");
        String takeButton = Localisation.getInstance().getStringValue("takeButton");

        String result = GUIManager.getInstance().waitForUserButtonPress(descriptionLabel, moveButton, takeButton);

        if (result.equals(moveButton)) {
            moveToSquare = playerCurrentSquareIndex + 1;
            if(moveToSquare > 24) moveToSquare = moveToSquare%24; //-1;

            Square square = game.getBoard().getSquareAtIndex(moveToSquare);
            game.movePlayer(playerIndex, square);
        } else {
         game.getDeck().drawCard(null).use();
        }
    }

    private void moveUpTo(Game game,int playerIndex){
        String moveButton1 = Localisation.getInstance().getStringValue("moveButton1");
        String moveButton2 = Localisation.getInstance().getStringValue("moveButton2");
        String moveButton3 = Localisation.getInstance().getStringValue("moveButton3");
        String moveButton4 = Localisation.getInstance().getStringValue("moveButton4");
        String moveButton5 = Localisation.getInstance().getStringValue("moveButton5");
        int playerCurrentSquareIndex = game.getPlayers()[playerIndex].getCurrentSquareIndex();
        int moveToSquare;
        int moveForwardChosen = 0;

        String moveUpToResult = GUIManager.getInstance().waitForUserButtonPress(descriptionLabel,moveButton1,moveButton2,moveButton3,moveButton4,moveButton5);

        switch (moveUpToResult){
            case "moveButton1" -> moveForwardChosen=1;
            case "moveButton2" -> moveForwardChosen=2;
            case "moveButton3" -> moveForwardChosen=3;
            case "moveButton4" -> moveForwardChosen=4;
            case "moveButton5" -> moveForwardChosen=5;
            default ->
                    moveUpTo(game,playerIndex);  // recursion. If no button chosen -> call moveUpTo() again;
        }
        moveToSquare = playerCurrentSquareIndex + moveForwardChosen;
        if(moveToSquare > 24) moveToSquare = moveToSquare%24; //-1;

        Square square = game.getBoard().getSquareAtIndex(moveToSquare);
        game.movePlayer(playerIndex,square);
    }

    private void giveCardToFigure(Game game,int playerIndex){
        // Find out how to check figure
    }

    private void move(Game game,int playerIndex) {
        //int playerIndex = game.getCurrentPlayer();
        GUIManager.getInstance().waitForUserButtonPress(descriptionLabel);
        Square square = game.getBoard().getSquareAtIndex(moveToSquare);
        game.movePlayer(playerIndex, square);
    }


}
