package com.gruppe21.game;

import com.gruppe21.Board.Board;
import com.gruppe21.game.board.Board;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    @Test
    public void getSquareAtNumberReturnsString() {
        Board board = new Board();

        assertEquals("hvidovrevej", board.getSquareAtIndex(4).getNameLabel());
    }

    @Test
    public void getSquare() {
    }
}