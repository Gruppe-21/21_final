package com.gruppe21.gui;

import com.gruppe21.game.board.squares.*;
import com.gruppe21.player.Player;
import com.gruppe21.utils.arrayutils.OurArrayList;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIWrapper {
    private GUI gui;
    private final OurArrayList<GUI_Field> fields;
    private final OurArrayList<GUI_Player> players;

    public GUIWrapper() {
        fields = new OurArrayList<GUI_Field>();
        players = new OurArrayList<GUI_Player>();
    }

    private void addSquares(OurArrayList<Square> squareList) {
        for (Square square : squareList.toArray(new Square[0])) {

        GUI_Field field = null;
        String subtext = "";
           if(square.getClass() == PropertySquare.class){
               PropertySquare p = (PropertySquare)square;
               field = new GUI_Street();
               field.setBackGroundColor(p.getColor());
               subtext = p.getSubtext();
           }
           else if(square.getClass() == StartSquare.class){
               field = new GUI_Start();

            //   field.setBackGroundColor((Color.WHITE));
           }
           else if(square.getClass() == PrisonSquare.class){
               field = new GUI_Jail();
           }
           else if(square.getClass() == ChanceSquare.class){
               field = new GUI_Chance();
           }
           else{
               field = new GUI_Street();
           }


            field.setTitle(square.getName());
            setFieldSubtext(field, subtext);

            field.setDescription(square.getDescriptionLabel());
            fields.add(field);

        }
    }

    public void setFieldTitle(Square square, String title){
        GUI_Field field = getFieldFromSquare(square);
        setFieldTitle(field, title);
    }

    public void setFieldTitle(GUI_Field field, String title){
        if (field != null) field.setTitle(title);
    }

    public void setFieldSubtext(Square square, String subtext){
        GUI_Field field = getFieldFromSquare(square);
        setFieldSubtext(field, subtext);
    }

    public void setFieldSubtext(GUI_Field field, String subtext){
        if (field != null) field.setSubText(subtext);
    }


    public void setFieldColor(Square square, Color color){
        GUI_Field field = getFieldFromSquare(square);
        setFieldColor(field, color);
    }

    public void setFieldColor(GUI_Field field, Color color){
        if (field != null) field.setBackGroundColor(color);
    }

    public GUI_Field getFieldFromName(String name){
        for (GUI_Field field : fields.toArray(new GUI_Field[0])) {
            String guiTitle = field.getTitle().replaceFirst("<*>", "");
            if (guiTitle.equals(name)) {
                return field;
            }
        }
        return null;
    }

    public GUI_Field getFieldFromSquare(Square square) {
        String squareName = (square.getClass() == PropertySquare.class ? ((PropertySquare)square).getGUIName() : square.getName());
        return getFieldFromName(squareName);
    }


    public void updateGUIFields(){
        updateGUIFields(fields.toArray(new GUI_Field[0]));
    }
    public void updateGUIFields(GUI_Field[] fields){
        if (fields.length > gui.getFields().length) return; //should probably throw exception
        for (int i = 0; i < fields.length; i++) {
            gui.getFields()[i] = fields[i];
        }
    }

    // Has to be called every time squares are added; Most likely only at the start of the game.
    public void reloadGUI(OurArrayList<Square> squareList) {
        addSquares(squareList);
        close();
        gui = new GUI(fields.toArray(new GUI_Field[0]), Color.WHITE);
    }


    // First add players after gui reload
    public void addPlayer(Player player, Color color) {

        // Don't add if player is null
        if (player == null)
            return;
        // Check if player is already added
        for (GUI_Player pl : players.toArray(new GUI_Player[0])) {
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

    public void close() {
        if (gui != null)
            gui.close();
    }

    public void setDice(int val1, int val2) {
        if(val2 <= 0){
            gui.setDice(val1, 4, 5, val1, 4, 5);
        }
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

    public boolean getUserLeftButtonPress(String message, String leftButtonText, String rightButtonText){
        return getUserLeftButtonPress(message, leftButtonText, rightButtonText);
    }

    public String getUserSelection(String message, String... options){
        return gui.getUserSelection(message, options);
    }

    // Get a string input from player
    public String getButtonPress(String message, String... buttonText) {
        return gui.getUserButtonPressed(message, buttonText);
    }

    public void updatePlayerBalance(GUI_Player guiPlayer, int balance) {
        if (guiPlayer != null) {
            guiPlayer.setBalance(balance);
        }
    }


    /*
    public GUI_Player getPlayer(int playerIndex) {
        //Maybe should make sure that playerIndex isn't out of bounds
        return players.get(playerIndex);
    }

    public GUI_Player getPlayer(String name) {
        for (GUI_Player player : players.toArray(new GUI_Player[0])) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }
     */

    public Boolean hasPlayerWithName(String name) {
        for (GUI_Player player : players.toArray(new GUI_Player[0])) {
            if (player.getName().split(" ")[0].equals(name)) return true;
        }
        return false;
    }
}
