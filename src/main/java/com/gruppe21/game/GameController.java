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
        gameView.selectLanguage();
        game = new Game(new Board(), gameView.askNumberOfPlayers(Game.MIN_PLAYERS, Game.MAX_PLAYERS));
        gameView.displayBoard(game.getBoard());
        initPlayers();
    }

    private void initPlayers(){
        for (int i = 0; i < game.getNumPlayers(); i++) {
            game.getPlayerControllers()[i] = new PlayerController();
            game.getPlayerControllers()[i].teleportTo(game.getBoard().getFirstSquareController()); //Maybe this happens automatically
        }
        game.setNextPlayer(gameView.askForFirstPlayer(game.getPlayerControllers()));
    }

    /**
     *
     */
    public void startGame(){
        while (game.getNumPlayers() > 1){
            doRound(game.nextPlayer());
        }
    }

    /**
     *
     * @param playerController
     */
    private void doRound(PlayerController playerController){
        playerController.takeTurn(game.getBoard());
    }
    

}
