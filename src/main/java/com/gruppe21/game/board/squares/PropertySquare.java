package com.gruppe21.game.board.squares;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class PropertySquare extends Square {

    private int price;
    private Color color;
    private Player owner = null;

    public PropertySquare(String nameLabel, String descriptionLabel, int price, Color color) {
        super(nameLabel, descriptionLabel);
        this.price = price;
        this.color = color;
    }

    public void purchaseProperty(Player player) {
        purchaseProperty(player, this.price);
    }

    public void purchaseProperty(Player player, int price) {
        if (player != null) {
            if (getOwner() == player || player.getOwnedProperties().size() >= Player.getMaxNumProperties()) return;
            player.getBankBalance().addBalance(-price);
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
        if (this.getOwner() == null){
            String text = localisation.getStringValue("buyplace", getName(), Integer.toString(price));
            guiManager.waitForUserAcknowledgement(text);
            purchaseProperty(player);
        }
        else if(this.getOwner() != player){
            int rent = price;
            //Only works becuase each color has two and only two fields
            for (PropertySquare propertySquare: getOwner().getOwnedProperties().toArray(new PropertySquare[0])){
                if(propertySquare != this && propertySquare.getColor() == this.getColor()){
                    rent *= 2;
                    break;
                }
            }
            String text = localisation.getStringValue("payRent", this.getOwner().getName(), Integer.toString(rent));
            guiManager.waitForUserAcknowledgement(text);
            player.getBankBalance().addBalance(rent, owner);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setOwner(Player owner) {
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