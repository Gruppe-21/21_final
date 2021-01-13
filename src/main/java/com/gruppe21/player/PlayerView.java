package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.utils.localisation.Localisation;
import gui_fields.GUI_Car;
import gui_fields.GUI_Player;

import java.awt.*;

public class PlayerView {
    Localisation localisation;
    GUIManager guiManager;

    public PlayerView(){
        localisation = Localisation.getInstance();
        guiManager = GUIManager.getInstance();
    }

    public int startTurn(Player model) {
        return guiManager.getUserButtonPressed(
                localisation.getStringValue("start_turn_msg_label", model.getName()),
                localisation.getStringValue("start_turn_button_label"),
                localisation.getStringValue("purchase_buildings_button_label"),
                localisation.getStringValue("liquidate_assets_button_label"));
    }


    /**
     *
     * @param diceValues
     */
    public void rollDice(int... diceValues){
        //tells the player to roll
        guiManager.waitForUserAcknowledgement(localisation.getStringValue("rollDice")); //TODO: use localisation
        //What if there isn't two values?
        guiManager.rollDice(diceValues[0], diceValues[1]);
    }

    public String chooseName(int minLength, int maxLength){
        String name = "";
        name = guiManager.getUserTextInput(Localisation.getInstance().getStringValue("request_player_name"), minLength, maxLength, true).trim();
        while (name.isEmpty()){
            name = guiManager.getUserTextInput(Localisation.getInstance().getStringValue("request_player_name_fail"), minLength, maxLength, true).trim();
        }
        return name;
    }

    public GUI_Car customiseCar(){
        //TODO: ask player to customise their car.
        return new GUI_Car(colors[colorToUse++], Color.black, GUI_Car.Type.CAR, GUI_Car.Pattern.FILL);
    }

    public void addToGui(GUI_Player guiPlayer){
        guiManager.addPlayer(guiPlayer);
    }

    public void imprisonedDiceCheater(){
        guiManager.waitForUserAcknowledgement(Localisation.getInstance().getStringValue("dice_cheater"));
    }

    /**
     * Asks the player to choose a method of release from prison.
     * @param hasPardon true if the player can use a <code>PardonCard</code.>
     * @param mayRollForFreedom true if the player may roll to be released.
     * @return an {@code int} representing the players choice. 49 ('1') means they chose to use a pardon,
     *         50 ('2') means they chose to roll the dice and 51 ('3') means they chose to pay.
     */
    public int chooseJailRemoval(boolean hasPardon, boolean mayRollForFreedom){
        String[] options = new String[1 + (hasPardon ? 1 : 0) + (mayRollForFreedom ? 1 : 0)];
        for (int i = 0; i < options.length; i++) {
            options[i] = i + ": JAIL REMOVAL CHOICE " + i + ", PlayerView chooseJailRemoval";
        }

        int choice = GUIManager.getInstance().getUserChoiceDropDown("CHOOSE JAIL REMOVAL MESSAGE, PlayerView chooseJailRemoval").charAt(0) + (hasPardon ? 0 : 1);
        return choice + ( (mayRollForFreedom && choice == 49) ? 0 : 1);
    }

    /**
     *
     * @param name
     * @param price
     * @param liquidateAssets
     * @return
     */
    public boolean askPurchase(String name, int price, boolean liquidateAssets){
        String purchaseText = Localisation.getInstance().getStringValue("purchase_text");
        String priceText = Localisation.getInstance().getStringValue("price_text");
        String liquidateAssetsText = Localisation.getInstance().getStringValue("liquidate_assets_text");
        String yesText = Localisation.getInstance().getStringValue("yes");
        String noText = Localisation.getInstance().getStringValue("no");

        return guiManager.getUserBoolean(purchaseText + name + priceText + price  + (liquidateAssets ? liquidateAssetsText : ""), yesText, noText);
    }

    /**
     *
     * @param properties
     * @return
     */
    public PropertySquareController choosePropertyBuildBuilding(PropertySquareController[] properties){
        String[] choices = new String[properties.length];
        for (int i = 0; i < properties.length; i++) {
            choices[i] = properties[i].getName() + " " + properties[i].getBuildingCost();
        }

        String buildText = Localisation.getInstance().getStringValue("build_text");


        String choice = GUIManager.getInstance().getUserChoiceDropDown(buildText, choices);
        for (int i = 0; i < choice.length(); i++) {
            if (choice.equals(choices[i])) return properties[i];
        }
        return null;
        //Throw exception
    }

    /**
     *
     * @return
     */
    public int chooseHowToLiquidate(boolean optional) {
        String[] buttons;
        String SELL_PROPERTY = Localisation.getInstance().getStringValue("sell_property_menu_button");
        String MORTGAGE = Localisation.getInstance().getStringValue("mortgage_menu_button");
        String TRADE = Localisation.getInstance().getStringValue("trade_menu_button");
        String BACK = Localisation.getInstance().getStringValue("back_menu_button");
        String CHOOSE_METHOD = Localisation.getInstance().getStringValue("liquidation_method_button");

        if (optional) {
            buttons = new String[] {"1: " + SELL_PROPERTY, "2: " + MORTGAGE, "3: " + TRADE, "4: " + BACK};
        } else {
            buttons = new String[] {"1: " + SELL_PROPERTY, "2: " + MORTGAGE, "3: " + TRADE};
        }
        String choice = guiManager.getUserButtonPress(CHOOSE_METHOD, buttons);
        return (choice.charAt(0) - '1');
    }

    public OwnableSquareController chooseProperty(OwnableSquareController[] properties, String messageLabel){
        if (properties == null) return null;
        String[] names = new String[properties.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = properties[i].getName();
            if (names[i].isEmpty()) names[i] += "TEST";
        }
        String choice = guiManager.getUserChoiceDropDown(localisation.getStringValue(messageLabel), names);
        for (int i = 0; i < names.length; i++) {
            if (choice.equals(names[i])) return properties[i];
        }
        return null;
    }

    public OwnableSquareController choosePropertyToSell(OwnableSquareController[] properties){
        return chooseProperty(properties, "sell_property");

    }

    public OwnableSquareController choosePropertyToMortgage(OwnableSquareController[] properties){
        return chooseProperty(properties, "mortgage_property");
    }

    public OwnableSquareController choosePropertyToPayOffMortgage(OwnableSquareController[] properties){
        return chooseProperty(properties, "pay_off_mortgage");
    }

    public void crossStartMessage(){
        final String crossed_start = localisation.getStringValue("crossed_start");
        guiManager.waitForUserAcknowledgement(crossed_start);
    }


    //Temporary
    private static final Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.ORANGE, Color.CYAN, Color.magenta};
    private static int colorToUse = 0;
}
