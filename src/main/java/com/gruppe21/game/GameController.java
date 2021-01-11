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
    }

    public void initGame(){
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
        PlayerController first = gameView.askForFirstPlayer(game.getPlayerControllers());


        game.setNextPlayer(first);
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

    /**
     *
     * @return
     */
    public PlayerController[] getPlayerControllers(){
        return game.getPlayerControllers();
    }

    /**
     * Please don't use this if at all possible. Please.
     * @return If you don't know, then use something else. Or even if you do know, then still do something else.
     */
    public PlayerController getCurrentPlayer(){
        return game.getCurrentPlayer();
    }
    

}
