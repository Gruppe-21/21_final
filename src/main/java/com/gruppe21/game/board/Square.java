package com.gruppe21.game.board;

import com.gruppe21.player.Player;

public class Square {

    private final SquareType squareType;
    private String name;
    private String eventText;
    private int modifyValue;

    public Square(String _name, String _eventText, int _modifyValue, SquareType _squareType) {
        this.name = _name;
        this.eventText = _eventText;
        this.modifyValue = _modifyValue;
        this.squareType = _squareType;
    }

    public SquareType getSquareType() {
        return squareType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public int getModifyValue() {
        return modifyValue;
    }

    public void setModifyValue(int modifyValue) {
        this.modifyValue = modifyValue;
    }


    public String handleEvent(Player player) {
        player.getBankBalance().addBalance(this.getModifyValue());
        return (eventText);
    }


}
