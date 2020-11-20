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

    public ChanceCardMove(String descriptionLabel, MoveCardType cardType, String label, String color, PlayerPiece playerPiece) {
        super(descriptionLabel);
        this.cardType = cardType;
        this.label = label;
        this.color = ColorUtil.getColor(color);
        this.playerPiece = playerPiece;
    }

    @Override
    public void use(Game game, Player player) {

        switch (cardType){
            case MoveToSquare -> move(game,player, getPropertySquareFromLabel(game, label));
            case MoveUpTo -> moveUpTo(game,player);
            case Figure -> giveCardToFigure(game,player);
            case TakeOrMove -> takeCard(game,player);
            case FreeSquare -> freeColorSquare(game,player);
        }
    }


    private void freeColorSquare(Game game, Player player){
        PropertySquare[] vaildSquares = game.getBoard().getSquareWithColor(color);
        String[] vaildSquaresNameLabels = new String[vaildSquares.length];
        for (int i = 0; i < vaildSquares.length; i++) {
            vaildSquaresNameLabels[i] = vaildSquares[i].getNameLabel();
        }
        //Todo: should probably indicate if a square is already owned
        PropertySquare chosenSquare = getPropertySquareFromLabel() GUIManager.getInstance().waitForUserButtonPress(Localisation.getInstance().getStringValue(descriptionLabel, vaildSquaresNameLabels));
        game.teleportPlayer(player, property);
        Player propertyOwner = property.getOwner();
       if(propertyOwner != null){
           property.handleLandOn(player);
       }else{
           property.purchaseProperty(player, 0);
       }

    }

    private void takeCard(Game game, Player player) {
        Localisation localisation = Localisation.getInstance();
        String moveButton = localisation.getStringValue("moveButton");
        String takeButton = localisation.getStringValue("takeButton");

        String result = GUIManager.getInstance().waitForUserButtonPress(descriptionLabel, moveButton, takeButton);

        if (result.equals(moveButton)) {
            game.movePlayerBy(player, 1);
        } else {
            player.drawChanceCard(game.getDeck(), game);
        }
    }

    private void moveUpTo(Game game, Player player){
        int numMoveButtons = 5;
        Localisation localisation = Localisation.getInstance();
        String[] moveButtons = new String[numMoveButtons];
        for (int i = 0; i < numMoveButtons; i++) {
            moveButtons[i] = localisation.getStringValue("movebutton" + i);
        }

        int moveForwardChosen = 0;
        String moveUpToResult = GUIManager.getInstance().waitForUserButtonPress(descriptionLabel, moveButtons);
        for (int i = 0; i < numMoveButtons; i++) {
            if (moveUpToResult.equals(moveButtons[i])){
                moveForwardChosen = i;
                game.movePlayerBy(player, moveForwardChosen);
                return;
            }
        }
        //It should not be possible to get here
        moveUpTo(game, player);
    }

    private void giveCardToFigure(Game game, Player player){
        // TODO
    }

    private void move(Game game, Player player, Square target) {
        GUIManager.getInstance().waitForUserButtonPress(descriptionLabel);
        game.movePlayer(player, target);
    }

    private Square getPropertySquareFromLabel(Game game, String label) {
        for (PropertySquare square : (PropertySquare[]) (game.getBoard().getSquareOfClass(PropertySquare.class))) {
            if (square.getNameLabel() == label) return square;
        }
        return null;
    }


    public PlayerPiece getPlayerPiece() {
        return playerPiece;
    }
}
