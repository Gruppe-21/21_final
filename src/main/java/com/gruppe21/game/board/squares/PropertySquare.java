package com.gruppe21.game.board.squares;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class PropertySquare extends Square {

    private int price;
    private final Color baseColor;
    private Color color;
    private String subtext; //not strictly necessary
    private String GUIName; //necessary
    private Player owner = null;

    public PropertySquare(String nameLabel, String descriptionLabel, int price, Color baseColor) {
        super(nameLabel, descriptionLabel);
        this.price = price;
        this.baseColor = baseColor;
        this.color = this.baseColor;
        setGUIName(getName());
        setSubtext(generateSubtext());
    }

    private static final float TINT_ALPHA = 0.3f;
    public Color getTintedColor(Player player){
        if (player == null) return baseColor;
        Color playerColor = player.getGuiPlayer().getPrimaryColor();

        return new Color(
                (baseColor.getRed() * (1-TINT_ALPHA) + playerColor.getRed() * TINT_ALPHA)/255,
                (baseColor.getGreen() * (1-TINT_ALPHA) + playerColor.getGreen() * TINT_ALPHA)/255,
                (baseColor.getBlue() * (1-TINT_ALPHA) + playerColor.getBlue() * TINT_ALPHA)/255
                );
    }

    public void purchaseProperty(Player buyer) {
        purchaseProperty(buyer, getPrice());
    }

    public void purchaseProperty(Player buyer, int price) {
        //Todo: should probably take a seller aswell
        if (buyer != null) {
            if (getOwner() == buyer || buyer.getOwnedProperties().size() >= Player.getMaxNumProperties()) return;
            buyer.getBankBalance().transferMoney(price, getOwner());
        }
        setOwner(buyer);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        if (this.getOwner() == null) {
            String text = localisation.getStringValue("buyplace",
                    getName(), localisation.getStringValue("currency"),Integer.toString(getPrice()));
            guiManager.waitForUserAcknowledgement(text);
            purchaseProperty(player);
        } else if (this.getOwner() != player) {
            int rent = getRent();
            String text = localisation.getStringValue("payRent",
                    this.getOwner().getName(), localisation.getStringValue("currency"),Integer.toString(rent));
            guiManager.waitForUserAcknowledgement(text);
            player.getBankBalance().transferMoney(rent, owner);
        }
    }


    public void updateGuiInformation(){
        setColor(getTintedColor(this.owner));
        setSubtext(generateSubtext());
        String oldGUIName = getGUIName();
        setGUIName(generateGUIName());
        GUIManager.getInstance().updateGUISquare(this, oldGUIName);
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setOwner(Player owner) {
        if (owner == getOwner()) return;

        if (getOwner() != null){
            //this square is removed from the old owner's list of owned squares
            getOwner().getOwnedProperties().remove(this);
            for (PropertySquare pSquare: getOwner().getOwnedProperties().toArray(new PropertySquare[0])) {
                //if the old owner owns the other square of this color its gui information is updated
                if (pSquare != this && pSquare.getBaseColor().equals(this.getBaseColor())) pSquare.updateGuiInformation();
            }
        }
        this.owner = owner;
        getOwner().addProperty(this);
        //if the new owner owns the other square of this color its gui information is updated
        for (PropertySquare pSquare: getOwner().getOwnedProperties().toArray(new PropertySquare[0])) {
            if (pSquare != this && pSquare.getBaseColor().equals(this.getBaseColor())) {
                pSquare.updateGuiInformation();
            }
        }
        this.updateGuiInformation();
    }

    private void setColor(Color color) {
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public Player getOwner() {
        return owner;
    }


    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        //You do not pay rent if no one owns the property
        if (getOwner() == null) return 0;

        //Only works because there are two and only two squares of each color
        for (PropertySquare property: getOwner().getOwnedProperties().toArray(new PropertySquare[0])) {
            if (property != this && property.getBaseColor().equals(this.getBaseColor())) return getPrice() * 2;
        }
        return getPrice();
    }


    public int getPrice() {
        return this.price;
    }

    private String generateSubtext() {
        Localisation localisation = Localisation.getInstance();
        subtext = localisation.getStringValue("currency",  Integer.toString( (getOwner() == null ? getPrice() : getRent()) ));
        return subtext;
    }

    public String getSubtext() {
        return subtext;
    }

    private void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getGUIName() {
        return GUIName;
    }

    public String generateGUIName(){
        return getName() + "\n" + getOwner().getPieceAsString();
    }

    private void setGUIName(String GUIName) {
        this.GUIName = GUIName;
    }
/*
    public Boolean buy(Player player) {
        if (getOwner() != player && getOwner() == player){
            return false;
        }
        return true;
        }

 */
    }