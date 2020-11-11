package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.gui.GUIWrapper;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.util.List;

public class ChanceCardMove extends ChanceCard {
    private List<Square> validSquares; //Square-class is implemented later
    private int moveToSquare;
    private Boolean isTakeCard;
    private Boolean isFreeSquare;
    private Boolean isMoveUpTo;
    private Boolean isFigur;

    public ChanceCardMove(String description, int moveToSquare, Boolean isTakeCard, Boolean isFreeSquare, Boolean isMoveUpTo, Boolean isFigur) {
        super(description);
        this.moveToSquare = moveToSquare;
        this.isTakeCard = isTakeCard;
        this.isFreeSquare = isFreeSquare;
        this.isMoveUpTo = isMoveUpTo;
        this.isFigur = isFigur;
    }


    @Override
    public void use(Game game) { //Player-class is implemented later

        if(isFreeSquare){
            freeSquare();
        } else if (isTakeCard) {
            takeCard(game);
        } else if(isMoveUpTo) {
            moveUpTo(game);
        } else if(isFigur) {
            // isFigur(game);
        }else{
            move(game);
        }
    }


    public void freeSquare(){
        // Move to "color" and get it for free...
        // Make this one later.
    }
   
    private void takeCard(Game game) {
        String moveButton = Localisation.getInstance().getStringValue("moveButton");
        String takeButton = Localisation.getInstance().getStringValue("takeButton");

        String result = game.getGuiWrapper().getButtonPress(description, moveButton, takeButton);
        if (result.equals(moveButton)) {
           // move 1 square forward
        } else {
           // draw new chancecard
        }
    }

    private void moveUpTo(Game game){
        String moveButton1 = Localisation.getInstance().getStringValue("moveButton1");
        String moveButton2 = Localisation.getInstance().getStringValue("moveButton2");
        String moveButton3 = Localisation.getInstance().getStringValue("moveButton3");
        String moveButton4 = Localisation.getInstance().getStringValue("moveButton4");
        String moveButton5 = Localisation.getInstance().getStringValue("moveButton5");
        int playerIndex = game.getCurrentPlayer();
        int playerCurrentSquareIndex = game.getPlayers()[playerIndex].getCurrentSquareIndex(); // Try to get player through game.getCurrentIndex();
        int moveToSquare;
        int moveForwardChosen = 0;
        
        String moveToResult = game.getGuiWrapper().getButtonPress(description,moveButton1,moveButton2,moveButton3,moveButton4,moveButton5);

        switch (moveToResult){
            case "moveButton1" -> moveForwardChosen=1;
            case "moveButton2" -> moveForwardChosen=2;
            case "moveButton3" -> moveForwardChosen=3;
            case "moveButton4" -> moveForwardChosen=4;
            case "moveButton5" -> moveForwardChosen=5;
            default ->
                    moveUpTo(game);  // recursion. If no button chosen, call moveUpTo() again;
        }

        moveToSquare = playerCurrentSquareIndex + moveForwardChosen;

        if(moveToSquare > 23){
            moveToSquare = moveToSquare%23-1;
        }
        
        Square square = game.getBoard().getSquareAtNumber(moveToSquare);
        game.movePlayer(playerIndex,square);
    }



    public void move(Game game) {
        int playerIndex = game.getCurrentPlayer();
        Square square = game.getBoard().getSquareAtNumber(moveToSquare);
        game.movePlayer(playerIndex, square);
    }


}
