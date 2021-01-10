package com.gruppe21.squares.views;

import com.gruppe21.squares.models.OwnableSquare;

import java.awt.*;

public abstract class OwnableSquareView extends SquareView {
    Color borderColor = Color.black;

    /**
     *
     * @param model
     */
    public void updateOwner(OwnableSquare model) {
        if (model.getOwner() == null) {
            model.getGuiField().setBorder(borderColor);
        } else {
            Color[] borderColors = model.getOwner().getColors();
            if (borderColors[0].equals(borderColors[1])) borderColors[1] = borderColor;
            model.getGuiField().setBorder(borderColors[0], borderColors[1]);
            //model.getGuiField().setBorder(borderColors[0], borderColor);
        }
    }
}