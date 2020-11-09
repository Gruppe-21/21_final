package com.gruppe21.game.board;

/*
        Opretter en basic Square
 */

public class SquareCreator {
    private String name;

    public SquareCreator(String name) {
        this.name = name;
    }
}

/*
        Opretter en property square
 */

public class Property extends SquareCreator{
    private int modifyvalue;
    private boolean owned;
    private boolean ownedPair;

    public Property(String name, int modifyvalue, boolean owned, boolean ownedPair) {
        super(name);
        this.modifyvalue = modifyvalue;
        this.owned = owned;
        this.ownedPair = ownedPair;
    }
}

public class Chance extends SquareCreator{
    return 0; // Mangler at chance kortet er lavet
}
/*
        Creates prison visit with event text.
 */
public class PrisonVisit extends SquareCreator{
    String eventText;

    public PrisonVisit(String name, String eventText) {
        super(name);
        this.eventText = eventText;
    }
}