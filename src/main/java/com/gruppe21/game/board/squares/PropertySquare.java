package com.gruppe21.game.board.squares;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class PropertySquare extends Square {

    private int price;
    private final Color baseColor;
    private Color color;
    private String subtext;
    private Player owner = null;

    public PropertySquare(String nameLabel, String descriptionLabel, int price, Color baseColor) {
        super(nameLabel, descriptionLabel);
        this.price = price;
        this.baseColor = baseColor;
        this.color = this.baseColor;
        setSubtext(generateSubtext());
    }

    private static final float TINT_ALPHA = 0.7f;
    public Color getTintedColor(Player player){
        if (player == null) return baseColor;
        Color playerColor = player.getGuiPlayer().getPrimaryColor();

        return new Color(
                (baseColor.getRed() * (1-TINT_ALPHA) + playerColor.getRed() * TINT_ALPHA)/255,
                (baseColor.getGreen() * (1-TINT_ALPHA) + playerColor.getGreen() * TINT_ALPHA)/255,
                (baseColor.getBlue() * (1-TINT_ALPHA) + playerColor.getBlue() * TINT_ALPHA)/255
                );
    }

    public void purchaseProperty(Player player) {
        purchaseProperty(player, getPrice());
    }

    public void purchaseProperty(Player player, int price) {
        if (player != null) {
            if (getOwner() == player || player.getOwnedProperties().size() >= Player.getMaxNumProperties()) return;
            player.getBankBalance().transferMoney(price);
            player.addProperty(this);
        }
        if (getOwner() != null)
            getOwner().removeProperty(this);
        setOwner(player);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        if (this.getOwner() == null) {
            String text = localisation.getStringValue("buyplace", getName(), Integer.toString(getPrice()));
            guiManager.waitForUserAcknowledgement(text);
            purchaseProperty(player);
        } else if (this.getOwner() != player) {
            int rent = getRent();
            String text = localisation.getStringValue("payRent", this.getOwner().getName(), Integer.toString(rent));
            guiManager.waitForUserAcknowledgement(text);
            player.getBankBalance().transferMoney(rent, owner);
        }
    }



    public Color getBaseColor() {
        return baseColor;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        setColor(getTintedColor(this.owner));
        setSubtext(generateSubtext());
        GUIManager.getInstance().updateGUISquare(this);
        //else setColor(b);
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
            if (property != this && property.getColor() == this.getColor()) return getPrice() * 2;
        }
        return getPrice();
    }


    public int getPrice() {
        return this.price;
    }

    private String generateSubtext() {
        Localisation localisation = Localisation.getInstance();
        String subtext = getOwner() == null ? "" : (getOwner().getPieceAsString() + "\n");
        subtext += localisation.getStringValue("currency", Integer.toString(getPrice()));
        return subtext;
    }

    public String getSubtext() {
        return subtext;
    }

    private void setSubtext(String subtext) {
        this.subtext = subtext;
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