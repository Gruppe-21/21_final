package com.gruppe21.game.board;
import com.gruppe21.game.Game;
import com.gruppe21.player.BankBalance;
import com.gruppe21.player.Player;
/*
        Creates the Square class:
 */

public abstract class SquareCreator {
    private String name;    //skal loades fra XML
    private String description; //skal loades fra XML

    public SquareCreator(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void handleLandOn(Player player){
        // This should be empty
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

/*
        Creates a StartSquare class:
 */

class StartSquare extends SquareCreator{
    private int startBonus = 2;

    public StartSquare(String name, String description){
        super(name, description);
    }

    public void StartBonus(Player player){
        super.handleLandOn(player);
        player.getBankBalance().addBalance(startBonus);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        player.getBankBalance().addBalance(startBonus);
    }
}

/*
        Creates a PrisonSquare class:
 */

class PrisonSquare extends SquareCreator{

    public PrisonSquare(String name, String description){
        super(name,description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        //Nothing happens and it should be left empty
    }
}

/*
        Creates a FreeParking square class:
 */

class FreeParkingSquare extends SquareCreator{

    public FreeParkingSquare(String name, String description){
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
        //Nothing happens and it should be left empty
    }
}

/*
        Creates a GoToPrisonSquare class:
 */
class GoToPrisonSquare extends SquareCreator {

    public GoToPrisonSquare(String name, String description) {
        super(name, description);
    }

    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);


    }
}

/*
        Creates a ChanceSquare class:
 */


class ChanceSquare extends SquareCreator{

    public ChanceSquare(String name, String description){
        super(name, description);
    }
    @Override
    public void handleLandOn(Player player) {
        super.handleLandOn(player);
    }

    public drawCard(){
        // Træk et kort
    }
}

/*
        Creates a PropertySquare class:
 */

class PropertySquare extends SquareCreator{

    private int price; //skal loades fra XML
    private Color color; //skal loades fra XML
    private Player player = null;


    public PropertySquare(String name, String description,int price, Color color) {
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
    public getOwner() {
        return owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public buy(Player player) {
        // Fix kode her
        return //boolean;
    }
}
