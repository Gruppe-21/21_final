package com.gruppe21.game;


import com.gruppe21.board.Board;
import com.gruppe21.player.PlayerController;
import com.gruppe21.squares.controllers.SquareController;

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
        String[] names = new String[game.getNumPlayers()];
        for (int i = 0; i < game.getNumPlayers(); i++) {
            game.getPlayerControllers()[i] = new PlayerController(names);
            game.getPlayerControllers()[i].teleportTo(game.getBoard().getFirstSquareController()); //Maybe this happens automatically
            names[i] = game.getPlayerControllers()[i].getName();
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
            if (getCurrentPlayer().isBankrupt()){
                game.removePlayer(getCurrentPlayer());
            }
        }
        gameView.displayWinner(game.getCurrentPlayer());
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

    public int getHouses(int desiredNumber){
        return game.getHouses(desiredNumber);
    }

    public int getAvailableHouses(){
        return game.getAvailableHouses();
    }

    public int getHotels(int desiredNumber){
        return game.getHotels(desiredNumber);
    }

    public int getAvailableHotels(){
        return game.getAvailableHouses();
    }


//This really, really should not be here!
    public boolean crossedStart(SquareController startPosition, SquareController endPosition){
        Board board = game.getBoard();
        SquareController start = board.getFirstSquareController();
        if (startPosition == start || endPosition == start) return false;
        return board.getDistanceBetween(startPosition, start) < board.getDistanceBetween(endPosition, start);
    }

    //Please don't use this if possible
    public Board getBoard(){
        return game.getBoard();
    }
}
