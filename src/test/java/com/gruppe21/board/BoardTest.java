package com.gruppe21.board;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.utils.CardLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BoardTest {
    private  Board board;


    @BeforeEach
    void setUp() {
        CardLoader.cardsAdded = 0;
        board = new Board();
    }

    @AfterEach
    void tearDown() {
        CardLoader.cardsAdded = 0;
        board = null;
    }

    /**
     * Passes if the first square read from main_board.xml is the start square.
     */
    @Test
    void getSquareControllerFromId() {
        final SquareController squareController = board.getSquareControllerFromId(1);
        final String label = squareController.getLabel();
        assertEquals("start", label);
    }

    /**
     * Tries to find the closest square controller
     *
     */
    @Test
    void closestSquareController() {
        final SquareController square = board.getSquareControllerFromId(17);
        final SquareController actualClosestSquare = board.getSquareControllerFromId(19);
        final SquareController[] squaresArray = { board.getSquareControllerFromId(20), actualClosestSquare, board.getSquareControllerFromId(5)};

        SquareController computedClosestSquare = board.closestSquareController(square, squaresArray);
        assertEquals(actualClosestSquare.getLabel(), computedClosestSquare.getLabel());

    }

    @Test
    void getDistanceBetween() {
        final SquareController a = board.getSquareControllerFromId(10);
        final SquareController b = board.getSquareControllerFromId(20);
        int distance = board.getDistanceBetween(a,b);
        assertEquals(10, distance);
    }

    @Test
    void getSquareControllerRelativeTo() {
        final SquareController start = board.getSquareControllerFromId(1);
        final int distance = 9;
        final SquareController expected = board.getSquareControllerFromId(10);

        SquareController relative = board.getSquareControllerRelativeTo(start, distance);

        assertEquals(expected, relative);
    }


    @Test
    void getFirstSquareController() {
        SquareController first = board.getFirstSquareController();
        SquareController expected = board.getSquareControllers()[0];
        assertEquals(expected, first);
    }
}