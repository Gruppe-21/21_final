package com.gruppe21.game.board.squares;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;
import java.awt.color.ColorSpace;

public class PropertySquare extends Square {

    private int price;
    private final Color baseColor;
    private Color color;
    private Player owner = null;

    public PropertySquare(String nameLabel, String descriptionLabel, int price, Color baseColor) {
        super(nameLabel, descriptionLabel);
        this.price = price;
        this.baseColor = baseColor;
        this.color = this.baseColor;
    }

    private static final float TINT_ALPHA = 0.7f;
    public Color getPlayerTintedColor(Player player){
        if (player == null) return baseColor;
        Color playerColor = player.getGuiPlayer().getPrimaryColor();

        return new Color(
                (baseColor.getRed() * (1-TINT_ALPHA) + playerColor.getRed() * TINT_ALPHA)/255,
                (baseColor.getGreen() * (1-TINT_ALPHA) + playerColor.getGreen() * TINT_ALPHA)/255,
                (baseColor.getBlue() * (1-TINT_ALPHA) + playerColor.getBlue() * TINT_ALPHA)/255
                );
    }

    public void purchaseProperty(Player player) {
        purchaseProperty(player, this.price);
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
        setColor(getPlayerTintedColor(player));
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        Localisation localisation = Localisation.getInstance();
        GUIManager guiManager = GUIManager.getInstance();
        if (this.getOwner() == null){
            String text = localisation.getStringValue("buyplace", getName(), Integer.toString(price));
            guiManager.waitForUserAcknowledgement(text);
            purchaseProperty(player);
        }
        else if(this.getOwner() != player){
            int rent = price;
            //Only works becuase each color has two and only two fields
            for (PropertySquare propertySquare: getOwner().getOwnedProperties().toArray(new PropertySquare[0])){
                if(propertySquare != this && propertySquare.getBaseColor() == this.getBaseColor()){
                    rent *= 2;
                    break;
                }
            }
            String text = localisation.getStringValue("payRent", this.getOwner().getName(), Integer.toString(rent));
            guiManager.waitForUserAcknowledgement(text);
            player.getBankBalance().transferMoney(rent, owner);
        }
    }

    public void setColor(Color color) {
        this.color = color;
        GUIManager.getInstance().setSquareGUIColor(color, this);
    }

    public Color getColor(){
        return color;
    }

    public Color getBaseColor() {
        return baseColor;
    }

    public void setOwner(Player owner) {
        if (owner == null) setColor(baseColor);
        //else setColor(b);
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
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