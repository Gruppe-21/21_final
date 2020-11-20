package com.gruppe21.game;

import com.gruppe21.game.board.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void getSquareAtNumberReturnsTower() {
        Board board = new Board();

        assertEquals("Tower", board.getSquareAtIndex(2).getName());
    }

    @Test
    public void getSquareAtNumberReturnsWereWall() {
        Board board = new Board();

        assertEquals("The Werewall", board.getSquareAtIndex(10).getName());
    }

    @Test
    public void getSquareAtNumberReturnsNullAtLessThanOne() {
        Board board = new Board();

        assertEquals(null, board.getSquareAtIndex(1));

    }

}