package com.gruppe21.game;

import com.gruppe21.game.board.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void getSquareAtNumberReturnsString() {
        Board board = new Board();

        assertEquals("pizzeria", board.getSquareAtIndex(2).getNameLabel());
    }
}