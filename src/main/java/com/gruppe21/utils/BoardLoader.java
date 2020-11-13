package com.gruppe21.utils;

import com.gruppe21.game.board.*;
import com.gruppe21.utils.localisation.Localisation;
import com.gruppe21.utils.xmlutils.XMLUtil;
import jdk.javadoc.internal.tool.Start;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


/**
 * Used for getting board configurations by parsing xml documents
 */
public class BoardLoader {

    public static ArrayList<Square> loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument("/boards/" + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, "board");
        // NodeList cardNodes = getNodeListFromTag(doc, "cards");

        ArrayList<Square> squares = getSquaresFromNodeList(boardNodes);
        //   ArrayList<ChanceCard> chanceCards = getCardsFromNodeList(boardNodes);
        return squares;
    }

    private static ArrayList<Square> getSquaresFromNodeList(NodeList boardNodes) {
        ArrayList<Square> squares = new ArrayList<Square>();

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                addXMLSquareToArrayList(squares, tag);

            }
        }



        return squares;
    }

    //TODO SKAL ÆNDRES TIL TYPEN CHANCECARD NÅR DET ER IMPLEMENTERET
    private static ArrayList<String> getCardsFromNodeList(NodeList nodelist) {
        ArrayList<String> cards = new ArrayList<String>();

        for (int i = 0; i < nodelist.getLength(); i++) {
            Node nNode = nodelist.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) nNode;
            }

        }
        return cards;
    }

    private static void addXMLSquareToArrayList(ArrayList<SquareCreator> squares, Element tag) {
        Localisation localisation = new Localisation();

        String elementName = tag.getNodeName();
        switch (elementName) {
            case "StartSquare":
                // Add square
                squares.add(new StartSquare("GO!", "");
                break;

            case "PropertySquare":
                // Add square
                String name = tag.getAttribute("label");
                int price = parseInt(tag.getAttribute("price"));
                Color color = Color.getColor(tag.getAttribute("color"));
                String description = localisation.getStringValue("word1"); //Add actual description
                squares.add(new PropertySquare(name,description, price, color));
                break;

            case "ChanceSquare":
                // Add square
                String name1 = tag.getAttribute("label");
                squares.add(new ChanceSquare(name1, ""));
                break;

            case "FreeParkingSquare":
                // Add square
                String name2 = tag.getAttribute("label");
                squares.add(new FreeParkingSquare(name2,  ""));
                break;

            case "GoToPrisonSquare":
                // Add square
                String name3 = tag.getAttribute("label");
                squares.add(new GoToPrisonSquare(name3, "");
                break;

            case "PrisonSquare":
                // Add square
                String name4 = tag.getAttribute("label");
                squares.add(new PrisonSquare(name4, "");
                break;

            default:
                break;

        }
    }


}
