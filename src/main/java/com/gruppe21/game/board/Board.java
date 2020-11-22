package com.gruppe21.game.board;

import com.gruppe21.game.board.squares.PropertySquare;
import com.gruppe21.game.board.squares.Square;
import com.gruppe21.player.Player;
import com.gruppe21.utils.BoardLoader;
import com.gruppe21.utils.arrayutils.OurArrayList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;


public class Board {
    private OurArrayList<Square> squares;

    public Board()  {
        try {
            squares = BoardLoader.loadBoard("main_board");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        for (Square square : squares.toArray(new Square[0])) {
            square.setBoard(this);
        }


    }
    public OurArrayList<Square> getSquares() {
        return squares;
    }

    public PropertySquare[] getSquaresWithColor(Color... colors){
        OurArrayList<PropertySquare> colorSquares = new OurArrayList<>();
        for (Square square: getSquares().toArray(new Square[0])) {
            for (Color color: colors) {
                if (square.getClass() == PropertySquare.class && ((PropertySquare) square).getBaseColor() == color) {
                    colorSquares.add((PropertySquare) square);
                    break;
                }
            }
        }
        return colorSquares.toArray(new PropertySquare[0]);
    }

    public Square[] getSquaresOfClass(Class squareClass){
        OurArrayList<Square> squaresOfClass = new OurArrayList<>();
        for (Square square: getSquares().toArray(new Square[0])) {
            if (square.getClass() == squareClass) squaresOfClass.add(square);
        }
        return squaresOfClass.toArray(new Square[0]);
    }

    public Square getSquareFromLabel(String nameLabel) {
        for (Square square: getSquares().toArray(new Square[0])) {
            if (square.getNameLabel().equals(nameLabel)) return square;
        }
        return null;
    }

    public Square getSquareClosetToSquare(Square square, Square[] squares){
        if (square == null || squares == null || squares[0] == null) return null;
        int minDistance = getDistanceToSquare(square, squares[0]);
        Square closestSquare = squares[0];
        for (int i = 1; i < squares.length; i++) {
            if(squares[i] != null){
                int distance = getDistanceToSquare(square, squares[i]);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestSquare = squares[i];

                }
            }
        }
        return closestSquare;
    }

    public PropertySquare[] getAvailableProperties(PropertySquare... properties){
        OurArrayList<PropertySquare> availableProperties = new OurArrayList<>();
        for (PropertySquare property: properties) {
            if(property.getOwner() == null) availableProperties.add(property);
        }
        if (availableProperties.size() == 0) return null;
        return availableProperties.toArray(new PropertySquare[0]);
    }

    public PropertySquare[] getAvailableProperties(){
        return getAvailableProperties((PropertySquare[]) getSquaresOfClass(PropertySquare.class));
    }

    public PropertySquare[] getPropertiesNotOwnedBy(Player... players){
        PropertySquare[] properties = (PropertySquare[]) getSquaresOfClass(PropertySquare.class);
        OurArrayList<PropertySquare> propertiesNotOwned = new OurArrayList<>();
        for (PropertySquare property: properties) {
            Boolean owned = false;
            for (Player player: players) {
                if(property.getOwner() == player) owned = true;
            }
            if (!owned) propertiesNotOwned.add(property);
        }
        if (propertiesNotOwned.size() == 0) return null;
        return propertiesNotOwned.toArray(new PropertySquare[0]);
    }

    public int getDistanceToSquare(Square from, Square to){
        if (from == null || to == null) return -1;
        int indexFrom = getSquareIndex(from), indexTo = getSquareIndex(to);
        if (indexTo < indexFrom) return getSquares().size() - indexFrom + indexTo;
        else return indexTo - indexFrom;
    }
    public Square getSquareAtRelativePosition(Square square, int distance){
        return getSquareAtIndex( (getSquareIndex(square) + distance) % getSquares().size());
    }

    public Square getSquareAtIndex(int index) {
        Square square = squares.get(index);
        return square;
    }

    public int getSquareIndex(Square square) {
        return squares.indexOf(square);
    }

}
