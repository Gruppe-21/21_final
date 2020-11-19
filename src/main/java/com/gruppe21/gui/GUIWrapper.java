package com.gruppe21.gui;

import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.player.Player;
import com.gruppe21.utils.arrayutils.OurArrayList;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIWrapper {
    private GUI gui;
    private List<GUI_Field> fields;
    private List<GUI_Player> players;

    public GUIWrapper() {
        fields = new ArrayList<GUI_Field>();
        players = new ArrayList<GUI_Player>();
    }

    //TODO set the right values in fields
    // Add a list of squares and turn them into fields.
    private void addSquares(OurArrayList<Square> squareList) {
        for (Square square : squareList.toArray(new Square[0])) {
            GUI_Field field = new GUI_Street();
            field.setTitle(square.getName());
            field.setSubText("");
            field.setDescription(square.getDescription());
            if(square.getClass() == PropertySquare.class)
            {
                field.setBackGroundColor(((PropertySquare)square).getColor());
            }else {
                field.setBackGroundColor((Color.WHITE));
            }

            fields.add(field);

        }
    }

    // Has to be called every time new squares are added; Most likely only at the start of the game.
    public void reloadGUI(OurArrayList<Square> squareList) {
        if (gui != null)
            gui.close();

        addSquares(squareList);
        gui = new GUI(fields.toArray(new GUI_Field[0]), Color.WHITE);
    }

    // First add players after gui reload
    public void addPlayer(Player player, Color color) {

        // Don't add if player is null
        if (player == null)
            return;
        // Check if player is already added
        for (GUI_Player pl : players) {
            if (pl.getName().equals(player.getName()))
                return;
        }

        GUI_Player guiPlayer = new GUI_Player(player.getName() + " " + player.getPieceAsString());
        guiPlayer.setBalance(player.getBankBalance().getBalance());
        guiPlayer.getCar().setPrimaryColor(color);
        player.setGuiPlayer(guiPlayer);
        gui.addPlayer(guiPlayer);
        players.add(guiPlayer);
    }

    public void movePlayer(Player player, int nextSquareIndex) {
        GUI_Player guiPlayer = player.getGuiPlayer();
        if (guiPlayer != null) {
            fields.get(player.getCurrentSquareIndex() ).setCar(guiPlayer, false);
            fields.get(nextSquareIndex).setCar(guiPlayer, true);
        }
    }

    /*
        public void movePlayer(int playerIndex, int currentSquareIndex, int nextSquareIndex) {
            GUI_Player player = getPlayer(playerIndex);
            if (player != null) {
                fields.get(currentSquareIndex + 1).setCar(player, false);
                fields.get(nextSquareIndex + 1).setCar(player, true);
            }
        }
    */
    public void close() {
        if (gui != null)
            gui.close();
    }

    public void setDice(int val1, int val2) {
        gui.setDice(val1, 4, 5, val2, 6, 5);
    }

    // Show message to player
    public void showMessage(String message) {
        gui.showMessage(message);
    }

    // Get a string input from player
    public String getStringInput(String message) {
        return gui.getUserString(message);
    }

    // Get a string input from player
    public String getButtonPress(String message, String... buttonText) {
        return gui.getUserButtonPressed(message, buttonText);
    }

    /*
    public void updatePlayerBalance(int playerIndex, int balance) {
        GUI_Player guiPlayer = getPlayer(playerIndex);
        if (guiPlayer != null) {
            guiPlayer.setBalance(balance);
        }
    }
*/
    public void updatePlayerBalance(GUI_Player guiPlayer, int balance) {
        if (guiPlayer != null) {
            guiPlayer.setBalance(balance);
        }
    }

    public GUI_Player getPlayer(int playerIndex) {
        //Mabye should make sure that playerIndex isn't out of bounds
        return players.get(playerIndex);
    }

    public GUI_Player getPlayer(String name) {
        for (GUI_Player player : players) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }

    public Boolean hasPlayerWithName(String name) {
        for (GUI_Player player : players) {
            if (player.getName().split(" ")[0].equals(name)) return true;
        }
        return false;
    }


}
