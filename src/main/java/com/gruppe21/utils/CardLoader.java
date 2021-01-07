package com.gruppe21.utils;

import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CardLoader {

    public static String CARD_DIRECTORY = "/cards/";
    public static String TAG_CARD = "/cards/";

    public static OurArrayList<ChanceCard> loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(CARD_DIRECTORY + fileName);
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, TAG_CARD);

        return getCardsFromNodeList(cardNodes);
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
