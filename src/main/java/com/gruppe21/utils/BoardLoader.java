package com.gruppe21.utils;

import com.gruppe21.game.board.chancecard.*;
import com.gruppe21.game.board.squares.*;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.squares.controllers.CardSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.*;
import com.gruppe21.squares.views.SquareView;
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
    public static String TAG_CARD = "/cards/";



    public static SquareController[] loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        return getSquaresFromNodeList(boardNodes);
    }

    private static SquareController[] getSquaresFromNodeList(NodeList boardNodes) {
        SquareController[] squares = new SquareController[boardNodes.getLength()];


        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                squares[i] = ConvertFromXMLtoSquareController(tag);

            }
        }

        return squares;
    }

    private static SquareController ConvertFromXMLtoSquareController(Element tag) {

        String elementName = tag.getNodeName();


        switch (elementName) {
            case "MoneySquare":
                MoneySquare moneySquareModel = new MoneySquare(tag);
                SquareView moneyView = new SquareView();
                return new SquareController(moneySquareModel, moneyView);
            case "PropertySquare":
                PropertySquare propertyModel = new PropertySquare(tag);
                SquareView propertyView = new SquareView();
                return new PropertySquareController(propertyModel, propertyView);
            case "CardSquare":
                CardSquare cardSquareModel = new CardSquare(tag);
                SquareView cardView = new SquareView();
                return new SquareController(cardSquareModel, cardView);
            case "TeleportSquare":
                TeleportSquare teleportSquareModel = new TeleportSquare(tag);
                SquareView SquareView = new SquareView();
                return new SquareController(teleportSquareModel, SquareView);
            default:
                Square squareModel = new Square(tag);
                SquareView squareView = new SquareView();
                return new SquareController(squareModel, squareView);
        }
    }




}
