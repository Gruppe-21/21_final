package com.gruppe21;
import com.gruppe21.game.GameController;

public class App {
    public static void main(String[] args) {
            GameController game = GameController.getInstance();
            game.initGame();
            game.startGame();
    }
}

