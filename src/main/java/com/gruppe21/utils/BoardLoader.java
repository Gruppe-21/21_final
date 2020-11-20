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
    public static String TAG_CARD = "cards";



    public static OurArrayList<Square> loadBoard(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(BOARD_DIRECTORY + fileName);
        NodeList boardNodes = XMLUtil.getNodeListFromTag(document, TAG_BOARD);

        return getSquaresFromNodeList(boardNodes);
    }

    public static OurArrayList<ChanceCard> loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(CARD_DIRECTORY + fileName);
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, TAG_CARD);

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
        String name = tag.getAttribute("label");
        String priceStr = tag.getAttribute("price");
        int price = priceStr.isEmpty() ? 0 : parseInt(tag.getAttribute("price"));
        Color color = ColorUtil.getColor(tag.getAttribute("color"));
        String description = tag.getAttribute("description");

        switch (elementName) {
            case "StartSquare" -> squares.add(new StartSquare("go", "startdesc"));
            case "PropertySquare" -> squares.add(new PropertySquare(name, description, price, color));
            case "ChanceSquare" -> squares.add(new ChanceSquare("chance", "takecard"));
            case "FreeParkingSquare" -> squares.add(new FreeParkingSquare("freeparking", "freeparkingdesc"));
            case "GoToPrisonSquare" -> squares.add(new GoToPrisonSquare("gotoprison", "gotoprisondesc"));
            case "PrisonSquare" -> squares.add(new PrisonSquare("prison", "prisondesc", "paidrelease", 2));
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
