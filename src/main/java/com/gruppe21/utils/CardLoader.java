package com.gruppe21.utils;

import com.gruppe21.card.cardControllers.controllers.CardController;
import com.gruppe21.card.cardControllers.controllers.PardonCardController;
import com.gruppe21.card.model.*;
import com.gruppe21.card.moneyCards.controllers.GrantCardController;
import com.gruppe21.card.moneyCards.controllers.MoneyCardController;
import com.gruppe21.card.moneyCards.model.BuildingFeesCard;
import com.gruppe21.card.moneyCards.model.GrantCard;
import com.gruppe21.card.moneyCards.model.ModifyMoneyCard;
import com.gruppe21.card.view.CardView;
import com.gruppe21.card.moveCards.controllers.MoveRelativeCardController;
import com.gruppe21.card.moveCards.controllers.MoveToNearestCardController;
import com.gruppe21.card.moveCards.controllers.TeleportToNearestCardController;
import com.gruppe21.card.moveCards.model.MoveRelativeCard;
import com.gruppe21.card.moveCards.model.TeleportToNearestCard;
import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class CardLoader {

    private final static String CARD_DIRECTORY = "/cards/";
    private final static String TAG_CARD = "cards";
    private static CardController[] cards;
    public static int cardsAdded = 0;

    /**
     * Receives cards from XML
     * @param fileName
     * @return (chanceCards)
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static CardController[] loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(CARD_DIRECTORY + fileName); // Finder mappen /cards/ i resources
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, TAG_CARD); // Leder efter root-tag i /cards/ mappen

        int elementsCount = 0;

        for (int i = 0; i < cardNodes.getLength(); i++) {
            Node node = cardNodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elementsCount++;
            }
        }

        cards = new CardController[elementsCount];

        return getCardsFromNodeList(cardNodes); // Return alt indhold af root-tag (cards)
    }

    /**
     * Checks every child tags from root-tag (cards)
     * @param cardNodes
     * @return cards
     */
    private static CardController[] getCardsFromNodeList(NodeList cardNodes) {
        //CardController[] chanceCards = new CardController[32];

        for (int i = 0; i < cardNodes.getLength(); i++) {
            Node nNode = cardNodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) nNode;
                addXMLCardsToArray(cards, tag); // Går til nedenstående funktion
            }
        }
        return cards;
    }

    /**
     * Tags XML and loads them into an array
     * @param cards
     * @param tag
     */
    private static void addXMLCardsToArray(CardController[] cards, Element tag) {
        String elementName = tag.getNodeName();
        CardView view = new CardView();

        // Ud fra tag finder den forskellige oplysninger om kortene
        switch (elementName) {
            case "buildingFeeCard":
                BuildingFeesCard feeMoneyCardModel = new BuildingFeesCard(tag);
                MoneyCardController feeMoneyController = new MoneyCardController(view, feeMoneyCardModel);
                cards[cardsAdded] = feeMoneyController;
                cardsAdded++;
                break;
            case "grantMoneyCard":
                GrantCard grantCard = new GrantCard(tag);
                GrantCardController grantCardController = new GrantCardController(view, grantCard);
                cards[cardsAdded] = grantCardController;
                cardsAdded++;
                break;
            case "modifyMoneyCard":
                ModifyMoneyCard mMoney = new ModifyMoneyCard(tag);
                MoneyCardController moneyCardController = new MoneyCardController(view, mMoney);
                cards[cardsAdded] = moneyCardController;
                cardsAdded++;
                break;
            case "moveToNearestCard":
                TeleportToNearestCard nearestCard = new TeleportToNearestCard(tag);
                MoveToNearestCardController moveNearestControllerCard = new MoveToNearestCardController(view,nearestCard);
                cards[cardsAdded] = moveNearestControllerCard;
                cardsAdded++;
                break;
            case "teleportToNearestCard":
                TeleportToNearestCard tNearestCard = new TeleportToNearestCard(tag);
                TeleportToNearestCardController tNearestCardController = new TeleportToNearestCardController(view,tNearestCard);
                cards[cardsAdded] = tNearestCardController;
                cardsAdded++;
                break;
            case "moveRelativeCard":
                MoveRelativeCard relativeCard = new MoveRelativeCard(tag);
                MoveRelativeCardController moveRelativeCardController = new MoveRelativeCardController(view, relativeCard);
                cards[cardsAdded] = moveRelativeCardController;
                cardsAdded++;
                break;
            case "pardonCard":
                PardonCard pardonCard = new PardonCard(tag);
                PardonCardController pardonController = new PardonCardController(view, pardonCard);
                cards[cardsAdded] = pardonController;
                cardsAdded++;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
    }


}
