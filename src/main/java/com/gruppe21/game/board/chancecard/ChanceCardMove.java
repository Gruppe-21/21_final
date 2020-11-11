package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.Square;
import com.gruppe21.utils.localisation.Localisation;

import java.util.List;

public class ChanceCardMove extends ChanceCard {
    private List<Square> validSquares; //Square-class is implemented later
    private int moveToSquare;
    private Boolean takeCard;

    public ChanceCardMove(String description, int moveToSquare, Boolean takeCard) {
        super(description);
        this.moveToSquare = moveToSquare;
        this.takeCard = takeCard;
    }


    public void use(Game game) { //Player-class is implemented later
        if (takeCard) {
            takeCard(game);
        } else {
            move(game);
        }
    }

    public void move(Game game) {
        int playerIndex = game.getCurrentPlayer();
        Square square = game.getBoard().getSquareAtNumber(1);
        game.movePlayer(playerIndex, square);
    }

    private void takeCard(Game game) {
        String moveButton = Localisation.getInstance().getStringValue("moveButton");
        String takeButton = Localisation.getInstance().getStringValue("takeButton");

        String result = game.getGuiWrapper().getButtonPress(description, moveButton, takeButton);
        if (result.equals(moveButton)) {

        } else {

        }
    }


}
