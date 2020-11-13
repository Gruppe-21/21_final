package com.gruppe21.game.board.squares;

import com.gruppe21.player.Player;

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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setOwner(Player owner) {
        // Fix kode her, hvor bliver owner initialiseret? Kig på designklassediagram
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

    public Boolean buy(Player player) {
        // Fix kode her
        return true; //boolean;
    }
}
