package com.gruppe21.utils;

import com.gruppe21.deck.Deck;
import com.gruppe21.squares.controllers.CardSquareController;
import com.gruppe21.squares.controllers.OwnableSquareController;
import com.gruppe21.squares.controllers.PropertySquareController;
import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.*;
import com.gruppe21.squares.views.OwnableSquareView;
import com.gruppe21.squares.views.PropertySquareView;
import com.gruppe21.squares.views.SquareView;
import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Used for getting board configurations by parsing xml documents
 */
public class BoardLoader {

    public static String BOARD_DIRECTORY = "/boards/";
    public static String CARD_DIRECTORY = "/cards/";
    public static String TAG_BOARD = "board";
    public static String TAG_CARD = "cards";


    public static SquareController[] loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        SquareController[] squareControllers = getSquaresFromNodeList(boardNodes);
        Deck deck = new Deck(CARD_DIRECTORY);
        for (SquareController squareController: squareControllers) {
            if (squareController.getClass() == CardSquareController.class)
                ((CardSquareController) squareController).setDeck(deck);
        }

        return squareControllers;
    }

    private static SquareController[] getSquaresFromNodeList(NodeList boardNodes) {
        int elementsCount = 0;

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elementsCount++;
            }
        }

        SquareController[] squares = new SquareController[elementsCount];
        int currentElementIndex = 0;
        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                squares[currentElementIndex] = addXMLSquareToArrayList(tag);
                currentElementIndex++;
            }
        }

        return squares;
    }

    private static SquareController addXMLSquareToArrayList(Element tag) {
        String elementName = tag.getNodeName();
        switch (elementName) {
            case "StartSquare":
                MoneySquare moneySquareModel = new MoneySquare(tag);
                moneySquareModel.setSquareType(SquareType.Start);
                SquareView moneyView = new SquareView();
                return new SquareController(moneySquareModel, moneyView);
            case "PropertySquare":
                PropertySquare propertyModel = new PropertySquare(tag);
                propertyModel.setSquareType(SquareType.Property);
                PropertySquareView propertyView = new PropertySquareView();
                return new PropertySquareController(propertyModel, propertyView);
            case "Brewery":
                BrewerySquare breweryModel = new BrewerySquare(tag);
                breweryModel.setSquareType(SquareType.Brewery);
                OwnableSquareView breweryView = new OwnableSquareView();
                return new OwnableSquareController(breweryModel, breweryView);
            case "Shipping":
                ShippingSquare shippingSquare = new ShippingSquare(tag);
                shippingSquare.setSquareType(SquareType.Shipping);
                OwnableSquareView shippingView = new OwnableSquareView();
                return new OwnableSquareController(shippingSquare, shippingView);
            case "ChanceSquare":
                CardSquare cardSquareModel = new CardSquare(tag);
                cardSquareModel.setSquareType(SquareType.Chance);
                SquareView cardSquareView = new SquareView();
                return new CardSquareController(cardSquareModel, cardSquareView);
            case "GoToPrisonSquare":
                TeleportSquare teleportSquareModel = new TeleportSquare(tag);
                teleportSquareModel.setSquareType(SquareType.Teleport);
                SquareView teleportSquareView = new SquareView();
                return new SquareController(teleportSquareModel, teleportSquareView);
            case "PrisonSquare":
                Square squareModel = new Square(tag);
                squareModel.setSquareType(SquareType.Prison);
                SquareView squareView = new SquareView();
                return new SquareController(squareModel, squareView);
            case "ParkingSquare":
                Square square1Model = new Square(tag);
                square1Model.setSquareType(SquareType.Parking);
                SquareView square1View = new SquareView();
                return new SquareController(square1Model, square1View);
            default:
                Square square2Model = new Square(tag);
                square2Model.setSquareType(SquareType.Teleport);
                SquareView square2View = new SquareView();
                return new SquareController(square2Model, square2View);
        }
    }


}
