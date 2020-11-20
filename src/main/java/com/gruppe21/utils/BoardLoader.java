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

    public static OurArrayList<Square> loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument("/boards/" + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, "board");

        return getSquaresFromNodeList(boardNodes);
    }

    public static OurArrayList<ChanceCard> loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument("/cards/" + fileName);
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, "cards");

        return getCardsFromNodeList(cardNodes);
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

    private static OurArrayList<ChanceCard> getCardsFromNodeList(NodeList boardNodes) {
        OurArrayList<ChanceCard> chanceCards = new OurArrayList<ChanceCard>();

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node nNode = boardNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) nNode;
                addXMLChanceCardToArrayList(chanceCards, tag);
            }

        }
        return chanceCards;
    }

    private static void addXMLSquareToArrayList(OurArrayList<Square> squares, Element tag) {
        Localisation localisation = new Localisation();

        String elementName = tag.getNodeName();
        switch (elementName) {
            case "StartSquare":
                // Add square
                squares.add(new StartSquare("go", ""));
                break;

            case "PropertySquare":
                // Add square
                String name = tag.getAttribute("label");
                int price = parseInt(tag.getAttribute("price"));
                Color color = ColorUtil.getColor(tag.getAttribute("color"));
                String description = tag.getAttribute("description"); //Add actual description
                squares.add(new PropertySquare(name, description, price, color));
                break;

            case "ChanceSquare":
                // Add square
                squares.add(new ChanceSquare("chance", "takecard"));
                break;

            case "FreeParkingSquare":
                // Add square
                squares.add(new FreeParkingSquare("freeparking", "freeparkingdesc"));
                break;

            case "GoToPrisonSquare":
                squares.add(new GoToPrisonSquare("gotoprison", "gotoprisondesc"));
                break;

            case "PrisonSquare":
                // Add square
                squares.add(new PrisonSquare("prison", "prisondesc", "", 2)); //Todo: fix this
                break;

            default:
                break;

        }
    }

    private static void addXMLChanceCardToArrayList(OurArrayList<ChanceCard> chanceCards, Element tag) {
        String elementName = tag.getNodeName();
        final String descriptionLabel = tag.getAttribute("description");

        switch (elementName) {
        case "moneycard" -> {
            final String moneyStr = tag.getAttribute("money");
            final int money = moneyStr.equals("") ? 0 : Integer.parseInt(moneyStr);
            final MoneyCardType type =  MoneyCardType.valueOf(tag.getAttribute("type"));
            chanceCards.add(new ChanceCardMoney(descriptionLabel, money, type));
        }
        //case "startcard" -> chanceCards.add(new ChanceCardStart(descriptionLabel));
        case "jailcard"  -> chanceCards.add(new ChanceCardGetOutOfJailFree(descriptionLabel));
        case "movecard"  -> {
            final String label = tag.getAttribute("label");
            final String color = tag.getAttribute("color");
            final MoveCardType type =  MoveCardType.valueOf(tag.getAttribute("type"));
            final String playerPieceStr = tag.getAttribute("piece");
            final PlayerPiece playerPiece =  playerPieceStr.isEmpty() ? PlayerPiece.Boat : PlayerPiece.valueOf(playerPieceStr);
            chanceCards.add(new ChanceCardMove(descriptionLabel, type, label, color, playerPiece));
        }
            default -> throw new IllegalStateException("Unexpected value: " + elementName);
        }
    }


}
