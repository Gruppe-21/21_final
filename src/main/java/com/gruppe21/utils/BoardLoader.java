package com.gruppe21.utils;

import com.gruppe21.game.board.chancecard.*;
import com.gruppe21.game.board.squares.*;
import com.gruppe21.player.PlayerPiece;
import com.gruppe21.squares.models.CardSquare;
import com.gruppe21.squares.models.MoneySquare;
import com.gruppe21.squares.models.PropertySquare;
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

    private static Square[] getSquaresFromNodeList(NodeList boardNodes) {
        Square[] squares = new Square[boardNodes.getLength()];

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node node = boardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) node;
                Square square = makeSquare(tag);
                squares[i] = square;

            }
        }

        return squares;
    }


    private static Square makeSquare(Element tag) {
        Localisation localisation = new Localisation();

        String elementName = tag.getNodeName();


        switch (elementName) {
            case "StartSquare":
                return new MoneySquare(tag);
                break;
            case "PropertySquare":
                return new PropertySquare(tag);
                break;
            case "ChanceSquare":
                return new CardSquare(tag);
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

    private static void addXMLChanceCardToArrayList(OurArrayList<ChanceCard> chanceCards, Element tag) {
        String elementName = tag.getNodeName();
        final String descriptionOnDrawLabel = tag.getAttribute("onDrawDescription");
        final String descriptionOnUseLabel = tag.getAttribute("onUseDescription");

        switch (elementName) {
            case "moneycard":
                final String moneyStr = tag.getAttribute("money");
                final int money = moneyStr.equals("") ? 0 : Integer.parseInt(moneyStr);
                final MoneyCardType type = MoneyCardType.valueOf(tag.getAttribute("type"));
                chanceCards.add(new ChanceCardMoney(null, descriptionOnUseLabel, money, type));
                break;
            case "jailcard":
                chanceCards.add(new ChanceCardGetOutOfJailFree(descriptionOnDrawLabel, descriptionOnUseLabel));
                break;
            case "movecard":
                final String label = tag.getAttribute("label");
                final String color = tag.getAttribute("color");
                final MoveCardType t = MoveCardType.valueOf(tag.getAttribute("type"));
                final String playerPieceStr = tag.getAttribute("piece");
                final PlayerPiece playerPiece = playerPieceStr.isEmpty() ? PlayerPiece.Boat : PlayerPiece.valueOf(playerPieceStr);
                chanceCards.add(new ChanceCardMove(null, descriptionOnUseLabel, t, label, playerPiece, color));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
    }


}
