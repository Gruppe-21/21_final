package com.gruppe21.game.board.squares;

import com.gruppe21.game.Game;
import com.gruppe21.player.Player;
import com.gruppe21.utils.localisation.Localisation;

import java.awt.*;

public class PropertySquare extends Square {

    private int price;
    private Color color;
    private Player owner = null;

    public PropertySquare(String name, String description, int price, Color color) {
        super(name, description);
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
    String text = Localisation.getInstance().getStringValue("buyplace", getName(), Integer.toString(price));
    purchaseProperty(player);
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