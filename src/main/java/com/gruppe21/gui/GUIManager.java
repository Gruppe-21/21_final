package com.gruppe21.gui;
import com.gruppe21.board.Board;
import com.gruppe21.squares.controllers.SquareController;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;

public class GUIManager {
    //Do we want to use display/set chance cards?

    private static final Color backgroundColor = Color.WHITE;

    private static GUIManager guiManager;

    private GUI gui;
    private GUI_Field[] fields;

    public static GUIManager getInstance(){
        if (guiManager == null) guiManager = new GUIManager();
        return guiManager;
    }

    /**
     *
     *
     */
    private GUIManager(){
        GUI_Street invisibleField = new GUI_Street();
        invisibleField.setBorder(Color.WHITE);
        invisibleField.setBackGroundColor(Color.WHITE);
        invisibleField.setForeGroundColor(Color.WHITE);
        gui = new GUI(new GUI_Field[] {invisibleField}, backgroundColor);
    }

    /**
     *
     *
     */
    /*
    public void addPlayers(PlayerController... playerControllers){
        //TODO: Figure out if players should be removed from the gui when they go bankrupt.
        for (PlayerController playerController: playerControllers) {
            gui.addPlayer(playerController.getGUIPlayer());
        }

    }
*/

    public void addPlayer(GUI_Player... gui_players){
        if (gui == null) return;
        for (GUI_Player gui_player: gui_players) {
            gui.addPlayer(gui_player);
        }
    }

    /**
     *
     * @param board
     */
    public void displayBoard(Board board){
        SquareController[] squareControllers = board.getSquareControllers();
        fields = new GUI_Field[squareControllers.length];
        for (int i = 0; i < squareControllers.length; i++) {
            fields[i] = squareControllers[i].getSquareField();
        }
        reloadGUI();
    }

    /**
     *
     */
    private void reloadGUI(){
       gui.close();
       gui = new GUI(fields, backgroundColor);
    }

    /**
     *
     */
    public void close(){
        if (gui != null) gui.close();
    }

    /**
     *
     * @param dieValueA
     * @param dieValueB
     * @return
     */
    public void rollDice(int dieValueA, int dieValueB){
        if (gui == null) return;
        //TODO: randomize rotation and position
        gui.setDice(dieValueA, dieValueB);
    }

    /**
     *
     * @param message
     */
    public void displayCard(String message){
        if (gui == null) return;
        gui.displayChanceCard(message);
    }


    //
    //Should this method even be here or should playerView do this itself?
    //
    /**
     *
     * @param player
     * @param field
     */
    public void setPlayerPosition(GUI_Player player, GUI_Field field){
        player.getCar().setPosition(field);
    }




    //Player inputs

    /**
     *
     * @param message
     */
    public void waitForUserAcknowledgement(String message) {
        if (gui == null) return;
        gui.showMessage(message);
    }



    /**
     *
     * @param message
     * @param trueChoice
     * @param falseChoice
     * @return
     */
    public boolean getUserBoolean(String message, String trueChoice, String falseChoice){
        if (gui == null) return Math.random() < 0.5;
        return gui.getUserLeftButtonPressed(message, trueChoice, falseChoice);
    }

    /**
     *
     * @param message
     * @param buttonText
     * @return
     */
    public String getUserButtonPress(String message, String... buttonText) {
        if (gui == null) return buttonText[(int) (Math.random() * (buttonText.length + 1))];
        return gui.getUserButtonPressed(message, buttonText);
    }

    /**
     *
     * @param message
     * @param buttonText
     * @return the number of the pressed button
     */
    public int getUserButtonPressed(String message, String... buttonText) {
        String pressedText = getUserButtonPress(message, buttonText);
        for (int i = 0; i < buttonText.length; i++) {
            if (pressedText.equals(buttonText[i])) return i;
        }
        return -1;
    }


    //Notice this is different from getUserBoolean, which previously was called get user choice
    /**
     *
     * @param message
     * @param options
     * @return
     */
    public String getUserChoiceDropDown(String message, String... options){
        if (gui == null) return options[(int) (Math.random() * (options.length + 1))];
        if(options == null) return "";
        return gui.getUserSelection(message, options);
    }


    /**
     *
     * @param message
     * @return
     */
    public String getUserTextInput(String message) {
        if (gui == null){
            return getUserTextInput(message, 5, 25, false);
        }
        return gui.getUserString(message);
    }

    /**
     *
     * @param message
     * @param minLength
     * @param maxLength
     * @param allowWhiteSpace
     * @return
     */
    public String getUserTextInput(String message, int minLength, int maxLength, boolean allowWhiteSpace) {
        if (gui == null){
            //Maybe should be in a different class. (Stringutils)
            String randASCIIString = "";
            int stringLength = minLength; // (int) (Math.random() * 21); //((25-5)+1) == 21
            for (int i = 0; i < stringLength; i++) {
                randASCIIString += ((int)(Math.random() * (('Z' - 'A') + 1))) + Math.random() < 0.5 ? 0 : 20;
            }
            return randASCIIString;
        }
        return gui.getUserString(message, minLength, maxLength, allowWhiteSpace);
    }


    /**
     *
     * @param message
     * @return
     */
    public int getUserInteger(String message){
        if (gui == null) return getUserInteger(message, 0, 10000);
        return gui.getUserInteger(message);
    }

    /**
     *
     * @param message
     * @param minValue
     * @param maxValue
     * @return
     */
    public int getUserInteger(String message, int minValue, int maxValue){
        if (gui == null) return (int) (Math.random() * (maxValue - minValue + 1));
        return gui.getUserInteger(message, minValue, maxValue);
    }











/*
    public static GUIManager instance;
    public boolean isTest;
    private GUIWrapper guiWrapper;
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public static GUIManager getInstance() {
        if (instance == null) {
            instance = new GUIManager();
        }
        return instance;
    }

    public void initGUI(Board board) {
        if (isTest) return;
        guiWrapper = new GUIWrapper();

        guiWrapper.reloadGUI(board.getSquares());
        selectLanguage(board.getSquares().toArray(new Square[0]));
    }

    public void addPlayersToGUI(Player[] players) {
        if (isTest) return;
        for (int i = 0; i < players.length; i++) {
            String realName = players[i].getName();
            String guiName = realName;

            int j = 2;
            while (guiWrapper.hasPlayerWithName(guiName)) {
                guiName = realName + " (" + j + ")";
                j++;
            }

            players[i].setName(guiName);
            guiWrapper.addPlayer(players[i], colors[i]);
            players[i].setName(realName);
            movePlayer(players[i], players[i].getCurrentSquareIndex());
        }
    }

    public void setGUIDice(Die[] dice) {
        if (isTest) return;
        //TODO: fix
        //guiWrapper.setDice(dice[0].getValue(), dice[1].getValue());
        guiWrapper.setDice(dice[0].getValue(), 0);
    }

    private static final int MAX_NUM_BUTTONS = 5;
    public static int getMaxNumButtons() {
        return MAX_NUM_BUTTONS;
    }

    public void updateGUISquare(Square square, String oldName){
        if (isTest) return;
        guiWrapper.setFieldTitle(guiWrapper.getFieldFromName(oldName), square.getGUIName());
        if (square.getClass() == PropertySquare.class)
            updateGUISquareOwner((PropertySquare) square);
        guiWrapper.updateGUIFields();
    }


    private void updateGUISquareOwner(PropertySquare square){
        if(guiWrapper == null){
            return;
        }
        guiWrapper.setFieldColor(square, square.getColor());
        guiWrapper.setFieldSubtext(square, square.getSubtext());
        guiWrapper.updateGUIFields();
    }

    public void setGUIPlayerBalance(Player player, int newBalance) {
        if(guiWrapper == null){
        return;
        }

        if (isTest) return;
        guiWrapper.updatePlayerBalance(player.getGuiPlayer(), newBalance);

 }

    public void movePlayer(Player player, int squareIndex) {
        if (isTest) return;
        guiWrapper.movePlayer(player, squareIndex);
    }

    public String getUserSelection(String message, String... options) {
        if (isTest) return null;
        if(guiWrapper == null){
            return "";
        }//we arbitrarily return true if it is a test
        return guiWrapper.getUserSelection(message, options);
    }

    public boolean getUserChoice(String message, String trueChoice, String falseChoice) {
        if (isTest) return false; //we arbitrarily return false if it is a test
        return guiWrapper.getUserLeftButtonPress(message, trueChoice, falseChoice);
    }

    public void waitForUserAcknowledgement(String message) {
        if (isTest) return;
        guiWrapper.showMessage(message);
    }

    public String waitForUserButtonPress(String message, String... buttonText) {
        if (isTest) return null;
        return guiWrapper.getButtonPress(message, buttonText);
    }

    public String waitForUserTextInput(String message) {
        if (isTest) return null;
        return guiWrapper.getStringInput(message);
    }

    public void closeGUI() {
        if (guiWrapper != null) guiWrapper.close();
    }

    public void selectLanguage(Square[] squares){
        String[] locales = Localisation.getInstance().getAllLocales();
        int numLocales = locales.length;
        String[] localeNames = new String[numLocales];
        for (int i = 0; i < numLocales; i++) {
            //Is all language codes are the same length using substring might be faster
            localeNames[i] = locales[i].split(" ")[1];
        }

        //If there were more than two options getUserSelection should be used instead
        String pressed = waitForUserButtonPress("Choose a language", localeNames);

        for (int i = 0; i < locales.length; i++) {
            if (localeNames[i].equals(pressed)){
                Localisation.getInstance().setCurrentLocale(locales[i].split(" ")[0]);
                break;
            }
        }

        for (Square square : squares) {
           GUI_Field field = guiWrapper.getFieldFromSquare(square);
        if(field != null) {


            String subtext = "";
            if (square.getClass() == PropertySquare.class) {
                PropertySquare p = (PropertySquare) square;
                field.setBackGroundColor(p.getColor());
                subtext = p.getSubtext();
            }

            field.setTitle(square.getName());
            guiWrapper.setFieldSubtext(field, subtext);
            field.setDescription(square.getDescriptionLabel());
        }

        }
        guiWrapper.updateGUIFields();
    }
*/
}
