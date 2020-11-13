package com.gruppe21.utils;

import com.gruppe21.game.board.squares.*;
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
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


/**
 * Used for getting board configurations by parsing xml documents
 */
public class BoardLoader {

    public static ArrayList<Square> loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument("/boards/" + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, "board");
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, "cards");

        ArrayList<Square> squares = getSquaresFromNodeList(boardNodes);
        ArrayList<ChanceCard> chanceCards = getCardsFromNodeList(boardNodes);

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
    private static ArrayList<ChanceCard> getCardsFromNodeList(NodeList boardNodes) {
        ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node nNode = boardNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) nNode;
                addXMLChanceCardToArrayList(chanceCards, tag);
            }

        }
        return chanceCards;
    }

    private static void addXMLSquareToArrayList(ArrayList<Square> squares, Element tag) {
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

    // Make labels?
    private static void addXMLChanceCardToArrayList(ArrayList<ChanceCard> chanceCards, Element tag) {
        String elementName = tag.getNodeName();
        switch (elementName) {
            case "jailcard":
                // Add card
                chanceCards.add(new ChanceCardGetOutOfJailFree("You can get out of jail for free if needed."));
                break;
            case "homeworkcard":
                // Add card
                chanceCards.add(new ChanceCardMoney("You have made your homework, receive 2#.",+2,false,true,false));
                break;
            case "candycard":
                // Add card
                chanceCards.add(new ChanceCardMoney("You have eaten to much candy. Pay 2# to the bank.",-2,true,false,false));
                break;
            case "birthdaycard":
                // Add card
                chanceCards.add(new ChanceCardMoney("It's your birthday! Everyone gives you 1#",+1,false,false,false));
                break;
            case "startcard":
                // Add card
                chanceCards.add(new ChanceCardStart("Move to the start area. Receive 2#",+2));
                break;
            case "boardwalkcard":
                // Add card
                chanceCards.add(new ChanceCardMove("Move to Strandpromenaden.", 23,false,false,false,false));
                break;
            case "moveuptocard":
                // Add card
                chanceCards.add(new ChanceCardMove("Move UP TO 5 squares forward.",0,false,false,true,false));
                break;
            case "cardOrmovecard":
                // Add card
                chanceCards.add(new ChanceCardMove("Move 1 square forward, OR take another chance card",0,true,false,false,false));
                break;
            default:
                break;

        }
    }


}
