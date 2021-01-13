package com.gruppe21.squares.models;

import com.gruppe21.utils.ColorUtil;
import org.w3c.dom.Element;

import java.awt.*;

import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class TaxSquare extends MoneySquare{
    private double tax;

    public TaxSquare(int id, String label, String description, Color color, int statusEffect, int modifyAmount, double tax) {
        super(id, label, description, color, statusEffect, modifyAmount);
        this.tax = tax;
    }

    public TaxSquare(Element xmlTag){
        this(!xmlTag.getAttribute("id").equals("") ? parseInt(xmlTag.getAttribute("id")) : 0, //Id
                xmlTag.getAttribute("label"), // Name ID
                xmlTag.getAttribute("description"), // Description ID
                ColorUtil.getColor(xmlTag.getAttribute("color")), // Color
                0,
                parseInt(xmlTag.getAttribute("fee")),
                parseDouble(xmlTag.getAttribute("tax"))
        );
    }

    public double getTax() {
        return tax;
    }
}
