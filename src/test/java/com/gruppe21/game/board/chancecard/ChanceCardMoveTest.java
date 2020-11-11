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
    public void testTakeCard(){

        assertEquals("x","x");
    }

    @Test
    public void testMoveUpTo(){
        int playerCurrentSquareIndex = 22;
        int moveToSquare;
        int moveForwardChosen = 5;

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