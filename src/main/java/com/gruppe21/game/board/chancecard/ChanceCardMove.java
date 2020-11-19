package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.utils.ColorUtil;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class ChanceCardMove extends ChanceCard {

    private final MoveCardType cardType;
    private final String label;
    private final Color color;
    private final PlayerPiece playerPiece;

    public ChanceCardMove(String description, MoveCardType cardType, String label, String color, PlayerPiece playerPiece) {
        super(description);
        this.cardType = cardType;
        this.label = label;
        this.color = ColorUtil.getColor(color);
        this.playerPiece = playerPiece;
    }




    @Override
    public void use(Game game, int playerIndex) {

        switch (cardType){
            case MoveToSquare -> move(game,playerIndex, getSquareFromLabel(game, label));
            case MoveUpTo -> moveUpTo(game,playerIndex);
            case Figure -> giveCardToFigure(game,playerIndex);
            case TakeOrMove -> takeCard(game,playerIndex);
            case FreeSquare -> freeColorSquare(game,playerIndex);
        }
    }


    private void freeColorSquare(Game game,int playerIndex){

        Player currentPlayer = game.getPlayers()[playerIndex];
        PropertySquare property = (PropertySquare) getSquareFromColor(game, color);
        GUIManager.getInstance().waitForUserButtonPress(descriptionLabel);
        game.teleportPlayer(currentPlayer, property);
        Player propertyOwner = property.getOwner();
       if(propertyOwner != null){
           property.handleLandOn(currentPlayer);
       }else{
           property.purchaseProperty(currentPlayer, 0);
       }

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
        // TODO
    }

    private void move(Game game,int playerIndex, Square target) {
        GUIManager.getInstance().waitForUserButtonPress(descriptionLabel);
        game.movePlayer(playerIndex, target);
    }

    private Square getSquareFromLabel(Game game,String label) {
        Square moveToSquare = null;
        for (Square square : game.getBoard().getSquares().toArray(new Square[0])) {
            if(square.getClass() == PropertySquare.class){
                PropertySquare property = (PropertySquare) square;
                if(property.getColor() == color){
                    moveToSquare = square;
                    break;
                }
            }
        }
        return moveToSquare;
    }

    private Square getSquareFromColor(Game game, Color color) {
        Square moveToSquare = null;
        for (Square square : game.getBoard().getSquares().toArray(new Square[0])) {

                if(square.getLabel().equals(label)){
                       moveToSquare = square;
                       break;
                   }
            }
        return moveToSquare;
    }


    public PlayerPiece getPlayerPiece() {
        return playerPiece;
    }
}
