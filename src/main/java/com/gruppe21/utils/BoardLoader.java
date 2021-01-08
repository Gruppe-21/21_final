package com.gruppe21.utils;

import com.gruppe21.game.board.chancecard.*;
import com.gruppe21.game.board.squares.*;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.squares.controllers.CardSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.Square;
import com.gruppe21.utils.arrayutils.OurArrayList;
import com.gruppe21.utils.localisation.Localisation;
import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static java.lang.Integer.parseInt;


/**
 * Used for getting board configurations by parsing xml documents
 */
public class BoardLoader {

    public static String BOARD_DIRECTORY = "/boards/";
    public static String CARD_DIRECTORY = "/cards/";
    public static String TAG_BOARD = "board";
    public static String TAG_CARD = "cards";



    public static Square[] loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        return getSquaresFromNodeList(boardNodes);
    }


    private static Square[] getSquaresFromNodeList(NodeList boardNodes) {
        Square[] squares = new Square[40];

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                addXMLSquareToArray(squares, tag);

            }
        }

        return squares;
    }


    private static void addXMLSquareToArray(Square[] squares, Element tag) {
        Localisation localisation = new Localisation();

        String elementName = tag.getNodeName();


        switch (elementName) {
            //case "StartSquare":
            //    return new SquareController;
            case "PropertySquare":
                return new PropertySquareController;
            case "ChanceSquare":
                return new CardSquareController;
            //case "FreeParkingSquare":
            //    return new SquareController;
            //case "GoToPrisonSquare":
            //    return new SquareController;
            //case "PrisonSquare";
            //    return new SquareController;
            else
                return new SquareController;
        }
    }




}
