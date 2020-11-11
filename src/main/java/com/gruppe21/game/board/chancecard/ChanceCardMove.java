package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.utils.localisation.Localisation;

public class ChanceCardMove extends ChanceCard {
    private int moveToSquare;
    private boolean isTakeCard;
    private boolean isFreeColorSquare;
    private boolean isMoveUpTo;
    private boolean isFigure;

    public ChanceCardMove(String description, int moveToSquare, boolean isTakeCard, boolean isFreeColorSquare, boolean isMoveUpTo, boolean isFigure) {
        super(description);
        this.moveToSquare = moveToSquare; // 0 if none?
        this.isTakeCard = isTakeCard;
        this.isFreeColorSquare = isFreeColorSquare;
        this.isMoveUpTo = isMoveUpTo;
        this.isFigure = isFigure;
    }


    @Override
    public void use(Game game) {
        if(isFreeColorSquare){
            freeColorSquare(game);
        } else if(isTakeCard) {
            takeCard(game);
        } else if(isMoveUpTo) {
            moveUpTo(game);
        } else if(isFigure) {
            giveCardToFigure(game);
        }else{
            move(game);
        }
    }
    

    public void freeColorSquare(Game game){
        int playerIndex = game.getCurrentPlayer();
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
   
    private void takeCard(Game game) {
        int playerIndex = game.getCurrentPlayer();
        int playerCurrentSquareIndex = game.getPlayers()[playerIndex].getCurrentSquareIndex();
        int moveToSquare;
        String moveButton = Localisation.getInstance().getStringValue("moveButton");
        String takeButton = Localisation.getInstance().getStringValue("takeButton");

        String result = game.getGuiWrapper().getButtonPress(description, moveButton, takeButton);

        if (result.equals(moveButton)) {
            moveToSquare = playerCurrentSquareIndex + 1;
            if(moveToSquare > 24) moveToSquare = moveToSquare%24; //-1;

            Square square = game.getBoard().getSquareAtNumber(moveToSquare);
            game.movePlayer(playerIndex,square);
        } else {
            //
            // draw new chancecard? how?
            //
        }
    }

    private void moveUpTo(Game game){
        String moveButton1 = Localisation.getInstance().getStringValue("moveButton1");
        String moveButton2 = Localisation.getInstance().getStringValue("moveButton2");
        String moveButton3 = Localisation.getInstance().getStringValue("moveButton3");
        String moveButton4 = Localisation.getInstance().getStringValue("moveButton4");
        String moveButton5 = Localisation.getInstance().getStringValue("moveButton5");
        int playerIndex = game.getCurrentPlayer();
        int playerCurrentSquareIndex = game.getPlayers()[playerIndex].getCurrentSquareIndex();
        int moveToSquare;
        int moveForwardChosen = 0;

        String moveUpToResult = game.getGuiWrapper().getButtonPress(description,moveButton1,moveButton2,moveButton3,moveButton4,moveButton5);

        switch (moveUpToResult){
            case "moveButton1" -> moveForwardChosen=1;
            case "moveButton2" -> moveForwardChosen=2;
            case "moveButton3" -> moveForwardChosen=3;
            case "moveButton4" -> moveForwardChosen=4;
            case "moveButton5" -> moveForwardChosen=5;
            default ->
                    moveUpTo(game);  // recursion. If no button chosen -> call moveUpTo() again;
        }
        moveToSquare = playerCurrentSquareIndex + moveForwardChosen;
        if(moveToSquare > 24) moveToSquare = moveToSquare%24; //-1;

        Square square = game.getBoard().getSquareAtNumber(moveToSquare);
        game.movePlayer(playerIndex,square);
    }

    public void giveCardToFigure(Game game){
        // Find out how to check figure
    }

    public void move(Game game) {
        int playerIndex = game.getCurrentPlayer();
        Square square = game.getBoard().getSquareAtNumber(moveToSquare);
        game.movePlayer(playerIndex, square);
    }


}
