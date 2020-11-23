package com.gruppe21.utils;

import java.awt.*;

public class ColorUtil {
    public static Color getColor(String col) {
        return switch (col.toLowerCase()) {
            case "black" -> Color.BLACK;
            case "blue" -> new Color(0, 40, 200);
            case "cyan" -> Color.CYAN.darker();
            case "darkgray" -> Color.DARK_GRAY;
            case "gray" -> Color.GRAY;
            case "green" -> new Color(0,150,50);
            case "yellow" -> new Color(242, 242, 0);
            case "lightgray" -> Color.LIGHT_GRAY;
            case "magenta" -> Color.MAGENTA;
            case "brown" -> Color.ORANGE.darker().darker(); //brown is actually dark orange in case you didn't know that
            case "orange" -> Color.ORANGE;
            case "pink" -> new Color(228, 161, 190).darker();
            case "red" -> new Color(240, 40, 40);
            case "white" -> Color.WHITE;
            default -> Color.white;
        };
    }
}
