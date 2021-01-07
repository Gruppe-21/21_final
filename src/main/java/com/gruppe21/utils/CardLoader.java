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
    public static String TAG_CARD = "cards";
    public static CardController[] cards = new CardController[32];
    public static int cardsAdded = 0;


    // Henter kort fra XML
    public static CardController[] loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(CARD_DIRECTORY + fileName); // Finder mappen /cards/ i resources
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, TAG_CARD); // Leder efter root-tag i /cards/ mappen

        return getCardsFromNodeList(cardNodes); // Return alt indhold af root-tag (cards)
    }

    // Kører igennem alle child tags af root-tag (cards)
    private static CardController[] getCardsFromNodeList(NodeList boardNodes) {
        CardController[] chanceCards = new CardController[32];

        for (int i = 0; i < boardNodes.getLength(); i++) {
            Node nNode = boardNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) nNode;
                addXMLChanceCardToArrayList(chanceCards, tag); // Går til nedenstående funktion
            }

        }
        return chanceCards;
    }


    //Tager XML og loader ind i et Array
    private static void addXMLChanceCardToArrayList(CardController[] chanceCards, Element tag) {
        String elementName = tag.getNodeName();
        final String descriptionOnDrawLabel = tag.getAttribute("onDrawDescription"); // Gemmer onDraw for alle kort
        final String descriptionOnUseLabel = tag.getAttribute("onUseDescription"); // Gemmer onDraw for alle kort

        // Ud fra tag finder den forskellige oplysninger om kortene
        switch (elementName) {
            case "moveRelativeCard":
                final String square_IDStr = tag.getAttribute("ID"); // Gemmer indholdet af ID-tag
                final int square_ID = square_IDStr.equals("") ? 0 : Integer.parseInt(square_IDStr); // Omdanner String til int

                MoveRelativeCard moveCardModel = new MoveRelativeCard(descriptionOnDrawLabel,descriptionOnUseLabel,square_ID);
                CardView moveView = new CardView();
                MoveRelativeCardController moveController = new MoveRelativeCardController(moveView,moveCardModel);

                cards[cardsAdded] = moveController;
                cardsAdded++;

                break;

            case "modifyMoneycard":
                final String moneyStr = tag.getAttribute("money");
                final int money = moneyStr.equals("") ? 0 : Integer.parseInt(moneyStr);

                ModifyMoneyCard moneyCardModel = new ModifyMoneyCard(descriptionOnDrawLabel,descriptionOnUseLabel,money);
                CardView moneyView = new CardView();
                MoneyCardController moneyController = new MoneyCardController(moneyView,moneyCardModel);

                cards[cardsAdded] = moneyController;
                cardsAdded++;

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
    }


}
