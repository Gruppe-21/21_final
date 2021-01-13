package com.gruppe21.utils;

import java.awt.*;

public class ColorUtil {
    public static Color getColor(String col) {
        //brown is actually dark orange in case you didn't know that
        switch (col.toLowerCase()) {
            case "black":
                return Color.BLACK;
            case "blue":
                return new Color(0, 40, 200);
            case "cyan":
                return Color.CYAN.darker();
            case "darkgray":
                return Color.DARK_GRAY;
            case "grey":
                return Color.GRAY;
            case "green":
                return new Color(0, 150, 50);
            case "yellow":
                return new Color(242, 242, 0);
            case "lightgray":
                return Color.LIGHT_GRAY;
            case "purple":
                return Color.magenta;
            case "brown":
                return Color.ORANGE.darker().darker();
            case "orange":
                return Color.ORANGE;
            case "pink":
                return new Color(255, 89, 146).darker();
            case "lightpink":
                return new Color(228, 161, 190).darker();
            case "red":
                return new Color(240, 40, 40);
            case "milk":
                return new Color(253, 255, 245);
            case "white":
                return Color.WHITE;
            case "business":
                return new Color(6, 143, 106);
            default:
                return Color.white;
        }
    }
}
