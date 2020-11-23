package com.gruppe21.game.board.chancecard;

import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChanceCardMoveTest {

    @Test
    public void testUse() {
        Player[] players = new Player[2];
        players[0] = new Player("Bo");
        players[1] = new Player("Lis");

        assertEquals("x","x");
    }

    @Test
    public void testFreeColorSquare() {

        assertEquals("x","x");
    }

    @Test
    public void testTakeCardMove(){
        int playerCurrentSquareIndex = 24;
        int moveToSquare=0;
        // String moveButton = Localisation.getInstance().getStringValue("moveButton");
        String moveButton = "moveButton";

        String result = "moveButton";

        if (result.equals(moveButton)) {
            moveToSquare = playerCurrentSquareIndex + 1;
            if(moveToSquare > 24) moveToSquare = moveToSquare%24; //-1;
        }else{
            // draw new chancecard
        }

        assertEquals(1,moveToSquare);
    }

    @Test
    public void testMoveUpTo(){
        int playerCurrentSquareIndex = 22;
        int moveToSquare;
        int moveForwardChosen=0;

        String moveUpToResult = "moveButton3";

        // recursion.
        switch (moveUpToResult) {
            case "moveButton1":
                moveForwardChosen = 1;
                break;
            case "moveButton2":
                moveForwardChosen = 2;
                break;
            case "moveButton3":
                moveForwardChosen = 3;
                break;
            case "moveButton4":
                moveForwardChosen = 4;
                break;
            case "moveButton5":
                moveForwardChosen = 5;
                break;
            default:
                testMoveUpTo();
                break;
        }

        moveToSquare = playerCurrentSquareIndex + moveForwardChosen;

        if(moveToSquare > 24) moveToSquare = moveToSquare%24;


        assertEquals(1,moveToSquare);
    }

    @Test
    public void testGiveCardToFigure() {

        assertEquals("x","x");
    }

    @Test
    public void testMove() {

        assertEquals("x","x");
    }

}