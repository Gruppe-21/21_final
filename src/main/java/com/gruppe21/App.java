package com.gruppe21;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // Start new game without predefined players
        Game game = new Game();
        //Game game = new Game(new Player[]{null, null});
        game.startGame();
    }
}

