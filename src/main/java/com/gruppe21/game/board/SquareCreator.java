package com.gruppe21.game.board;

/*
        Opretter en basic Square
 */

public class SquareCreator {
    private String name;
    private String description;

    public SquareCreator(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void handleLandOn(Player player){
        // Lav metoden færdig
    }

    public setDescription(String description ){
        // Lav metoden færdig
    }

    public getDescription(){
        // Lav metoden færdig
    }

    public setName(String name){
        // Lav metoden færdig
    }

    public getName(){
        // Lav metoden færdig
    }

}

/*
        Creates a StartSquare class:
 */

public class StartSquare extends SquareCreator{
    private int startBonus = 2;

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}

/*
        Creates a PrisonSquare class:
 */

public class PrisonSquare extends SquareCreator{
    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}

/*
        Creates a FreeParking square class:
 */

public class FreeParkingSquare extends SquareCreator{

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}

/*
        Creates a GoToPrisonSquare class:
 */
public class GoToPrisonSquare extends SquareCreator{

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}

/*
        Creates a ChanceSquare class:
 */


public class ChanceSquare extends SquareCreator{

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }
}

/*
        Creates a PropertySquare class:
 */

public class PropertySquare extends SquareCreator{
    private int price;
    private Color color;
    private Player player;
    //private boolean owned;
    //private boolean ownedPair;

    public PropertySquare(int price, Color color, Player player) {
        this.price = price;
        this.color = color;
        this.player = player;
    }
}
