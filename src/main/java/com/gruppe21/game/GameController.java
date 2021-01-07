package com.gruppe21.game;


import com.gruppe21.player.PlayerController;

public class GameController {
    private static GameController gameController;

    public static GameController getInstance(){
        if (gameController == null) gameController = new GameController();
        return gameController;
    }

    Game game;
    GameView gameView;
    private GameController(){
        gameView = new GameView();
        game = new Game(gameView.askNumberOfPlayers(Game.MIN_PLAYERS, Game.MAX_PLAYERS));
        initPlayers();
    }

    private void initPlayers(){
        for (int i = 0; i < game.getNumPlayers(); i++) {
            game.getPlayerControllers()[i] = new PlayerController();
        }
    }
    

}
