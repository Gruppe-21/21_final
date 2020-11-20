package com.gruppe21.utils;

import java.awt.*;

public class ColorUtil {
    public static Color getColor(String col) {
        return switch (col.toLowerCase()) {
            case "black" -> Color.BLACK;
            case "blue" -> Color.BLUE;
            case "cyan" -> Color.CYAN;
            case "darkgray" -> Color.DARK_GRAY;
            case "gray" -> Color.GRAY;
            case "green" -> Color.GREEN;
            case "yellow" -> Color.YELLOW;
            case "lightgray" -> Color.LIGHT_GRAY;
            case "magenta" -> Color.MAGENTA;
            case "orange" -> Color.ORANGE;
            case "pink" -> Color.PINK;
            case "red" -> Color.RED;
            case "white" -> Color.WHITE;
            default -> Color.white;
        };
    }
}
