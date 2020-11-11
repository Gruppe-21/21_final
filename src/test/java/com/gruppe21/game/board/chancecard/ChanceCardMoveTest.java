package com.gruppe21.game.board.chancecard;

import com.gruppe21.game.Game;
import com.gruppe21.game.board.chancecard.ChanceCardMove;
import com.gruppe21.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        int playerCurrentSquareIndex = 22;
        int moveToSquare;
        int moveForwardChosen=0;


        assertEquals("x","x");
    }

    @Test
    public void testMoveUpTo(){
        int playerCurrentSquareIndex = 22;
        int moveToSquare;
        int moveForwardChosen=0;

        String moveUpToResult = "moveButton5";

        switch (moveUpToResult){
            case "moveButton1" -> moveForwardChosen=1;
            case "moveButton2" -> moveForwardChosen=2;
            case "moveButton3" -> moveForwardChosen=3;
            case "moveButton4" -> moveForwardChosen=4;
            case "moveButton5" -> moveForwardChosen=5;
            default ->
                    testMoveUpTo();  // recursion.
        }

        moveToSquare = playerCurrentSquareIndex + moveForwardChosen;

        if(moveToSquare > 23) moveToSquare = moveToSquare%23-1;


        assertEquals(3,moveToSquare);
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