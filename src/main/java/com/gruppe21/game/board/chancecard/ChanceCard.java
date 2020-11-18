package com.gruppe21.game.board.chancecard;

public abstract class ChanceCard {
    protected String descriptionLabel;

    public ChanceCard(String descriptionLabel) {
        this.descriptionLabel = descriptionLabel;
    }

    // Used in ChanceCardMove
    public void use() {
        // Do something
    }
}
