package com.gruppe21.utils;

import com.gruppe21.game.board.chancecard.*;
import com.gruppe21.game.board.squares.*;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.utils.arrayutils.OurArrayList;
import com.gruppe21.utils.localisation.Localisation;
import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
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



    public static OurArrayList<Square> loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        return getSquaresFromNodeList(boardNodes);
    }

    private static OurArrayList<Square> getSquaresFromNodeList(NodeList boardNodes) {
        OurArrayList<Square> squares = new OurArrayList<Square>();

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                addXMLSquareToArrayList(squares, tag);

            }
        }

        return squares;
    }

    private static void addXMLSquareToArrayList(OurArrayList<Square> squares, Element tag) {
        Localisation localisation = new Localisation();

        String elementName = tag.getNodeName();
        String name = tag.getAttribute("label");
        String priceStr = tag.getAttribute("price");
        int price = priceStr.isEmpty() ? 0 : parseInt(tag.getAttribute("price"));
        Color color = ColorUtil.getColor(tag.getAttribute("color"));
        String description = tag.getAttribute("description");

        switch (elementName) {
            case "StartSquare":
                squares.add(new StartSquare("go", "startdesc"));
                break;
            case "PropertySquare":
                squares.add(new PropertySquare(name, description, price, color));
                break;
            case "ChanceSquare":
                squares.add(new ChanceSquare("chance", "takecard"));
                break;
            case "FreeParkingSquare":
                squares.add(new FreeParkingSquare("freeparking", "freeparkingdesc"));
                break;
            case "GoToPrisonSquare":
                squares.add(new GoToPrisonSquare("gotoprison", "gotoprisondesc"));
                break;
            case "PrisonSquare":
                squares.add(new PrisonSquare("prison", "prisondesc", "paidRelease", 2));
                break;
        }
    }


}
