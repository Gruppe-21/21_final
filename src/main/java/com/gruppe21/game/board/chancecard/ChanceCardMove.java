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
    private final Color[] colors;
    private final PlayerPiece playerPiece;

    public ChanceCardMove(String descriptionOnDrawLabel, String descriptionOnUseLabel, MoveCardType cardType, String label, PlayerPiece playerPiece, String... colors) {
        super(descriptionOnDrawLabel, descriptionOnUseLabel);
        this.cardType = cardType;
        this.label = label;
        this.colors = new Color[colors.length];
        for (int i = 0; i < colors.length; i++) {
            this.colors[i] = ColorUtil.getColor(colors[i]);
        }
        this.playerPiece = playerPiece;
    }

    @Override
    public void onDraw(Game game, Player player) {
        if (cardType == MoveCardType.Figure) {
            GUIManager.getInstance().waitForUserAcknowledgement(Localisation.getInstance().getStringValue(descriptionOnUseLabel, Player.playerPieceAsString(this.playerPiece)));
            giveCardToFigure(game, player);
        } else use(game, player);
    }

    @Override
    public void use(Game game, Player player) {
        super.use(game, player);
        switch (cardType) {
            case MoveToSquare:
                move(game, player, game.getBoard().getSquareFromLabel(label));
                break;
            case MoveUpTo:
                moveUpTo(game, player);
                break;
            case Figure:
                PropertySquare[] validSquares = game.getBoard().getAvailableProperties();
                if (validSquares == null) {
                    validSquares = game.getBoard().getPropertiesNotOwnedBy(player);
                }
                moveToSquareAndPurchase(game, player, validSquares);
                break;
            case TakeOrMove:
                takeCardOrMove(game, player);
                break;
            case FreeSquare:
                freeColorSquare(game, player);
                break;
        }
    }

    private void moveToSquareAndPurchase(Game game, Player player, PropertySquare... validSquare){
        if (validSquare.length == 0) {return;} //The player owns all properties. Something should be done; The player should at least be told.
        //Todo: pick square, move(teleport) to it and buy it. (Should probably inform the player if it will bankrupt them)

    }


    private void freeColorSquare(Game game, Player player){
        PropertySquare[] vaildSquares = game.getBoard().getSquaresWithColor(colors);
        String[] vaildSquaresNameLabels = new String[vaildSquares.length];
        for (int i = 0; i < vaildSquares.length; i++) {
            vaildSquaresNameLabels[i] = vaildSquares[i].getNameLabel();
        }
        //Todo: should probably indicate if a square is already owned
        PropertySquare chosenSquare = (PropertySquare) game.getBoard().getSquareFromLabel(GUIManager.getInstance().waitForUserButtonPress(Localisation.getInstance().getStringValue(descriptionOnUseLabel, vaildSquaresNameLabels)));
        game.teleportPlayer(player, chosenSquare);
        Player propertyOwner = chosenSquare.getOwner();
       if(propertyOwner != null){
           chosenSquare.handleLandOn(player);
       }else{
           chosenSquare.purchaseProperty(player, 0);
       }

    }

    private void takeCardOrMove(Game game, Player player) {
        Localisation localisation = Localisation.getInstance();
        String description = localisation.getStringValue(descriptionOnUseLabel);
        String moveButton = localisation.getStringValue("moveButton");
        String takeButton = localisation.getStringValue("takeButton");
        if (GUIManager.getInstance().getUserChoice(description, moveButton, takeButton)){
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
            moveButtons[i] = localisation.getStringValue("moveButton" + (i+1));
        }

        String moveUpToResult = GUIManager.getInstance().waitForUserButtonPress(Localisation.getInstance().getStringValue(descriptionOnUseLabel), moveButtons);
        for (int i = 0; i < numMoveButtons; i++) {
            if (moveUpToResult.equals(moveButtons[i])){
                game.movePlayerBy(player, i + 1);
                return;
            }
        }
        //It should not be possible to get here
        moveUpTo(game, player);
    }

    private void giveCardToFigure(Game game, Player user){
        for (Player player: game.getPlayers()) {
            if(player.getPiece() == getPlayerPiece()){
                player.getOwnedCards().add(this);
            }
        }
        user.drawChanceCard(game.getDeck(), game);
    }

    private void move(Game game, Player player, Square target) {
        GUIManager.getInstance().waitForUserButtonPress(Localisation.getInstance().getStringValue(descriptionOnUseLabel));
        game.movePlayer(player, target);
    }

    public PlayerPiece getPlayerPiece() {
        return playerPiece;
    }
}
