package com.gruppe21.game;

import com.gruppe21.game.board.Square;
import com.gruppe21.game.board.SquareType;
import com.gruppe21.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class SquareTest {

    @Test
    public void squareReturnsEventText() {
        Square sq = new Square("place", "Hello World!", 1000, SquareType.Normal);

        Assert.assertEquals("Hello World!", sq.getEventText());
    }

    @Test
    public void squareModifiesPlayerBalance() {
        Player player = new Player();
        Square sq = new Square("place", "Hello World!", 1000, SquareType.Normal);

        // TODO implement test for checking if square modifies a players bank balance
    }
}