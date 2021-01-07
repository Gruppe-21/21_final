package com.gruppe21.utils;

import com.gruppe21.squares.models.*;
import com.gruppe21.utils.localisation.Localisation;
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


    public static Square[] loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        return getSquaresFromNodeList(boardNodes);
    }

    private static Square[] getSquaresFromNodeList(NodeList boardNodes) {
        int elementsCount = 0;

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elementsCount++;
            }
        }
        Square[] squares = new Square[elementsCount];

        int currentElementIndex = 0;
        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                squares[currentElementIndex] = addXMLSquareToArrayList(tag);
            }
        }
        return squares;
    }

    private static Square addXMLSquareToArrayList(Element tag) {
        Localisation localisation = new Localisation();
        String elementName = tag.getNodeName();
        switch (elementName) {
            case "StartSquare":
                return new MoneySquare(tag);
            case "PropertySquare":
                return new PropertySquare(tag);
            case "ChanceSquare":
                return new CardSquare(tag);
            case "GoToPrisonSquare":
                return new TeleportSquare(tag);
            case "PrisonSquare":
            case "FreeParkingSquare":
            default:
                return new Square(tag);
        }
    }


}
