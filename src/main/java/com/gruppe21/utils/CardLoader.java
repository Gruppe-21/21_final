package com.gruppe21.utils;

import com.gruppe21.card.cardControllers.*;
import com.gruppe21.card.cardType.*;
import com.gruppe21.card.cardView.CardView;
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
    private static int cardsAdded = 0;

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
        final String descriptionOnDrawLabel = tag.getAttribute("onDrawDescription"); // Gemmer onDraw for alle kort
        final String descriptionOnUseLabel = tag.getAttribute("onUseDescription"); // Gemmer onDraw for alle kort

        // Ud fra tag finder den forskellige oplysninger om kortene
        switch (elementName) {
            case "buildingFeeCard":
                final String moneyHouseStr = tag.getAttribute("moneyHouse");
                final int moneyHouse = moneyHouseStr.equals("") ? 0 : Integer.parseInt(moneyHouseStr);
                final String moneyHotelStr = tag.getAttribute("moneyHotel");
                final int moneyHotel = moneyHotelStr.equals("") ? 0 : Integer.parseInt(moneyHotelStr);
                final String feeBankStr = tag.getAttribute("isBank");
                boolean feeIsBank = feeBankStr.equals("") ? false : Boolean.parseBoolean(feeBankStr);

                ModifyMoneyCard feeMoneyCardModel = new ModifyMoneyCard(descriptionOnDrawLabel,descriptionOnUseLabel, feeIsBank,moneyHouse, moneyHotel);
                CardView feeMoneyView = new CardView();
                MoneyCardController feeMoneyController = new MoneyCardController(feeMoneyView,feeMoneyCardModel);

                cards[cardsAdded] = feeMoneyController;
                cardsAdded++;
                break;
            case "legateMoneyCard":
                final String moneyLegateStr = tag.getAttribute("money");
                final String minMoneyLegateStr = tag.getAttribute("minMoney");
                final String legateBankStr = tag.getAttribute("isBank");
                final String IsLegateStr = tag.getAttribute("isLegate");
                boolean IsLegate = IsLegateStr.equals("") ? false : Boolean.parseBoolean(IsLegateStr);
                boolean legateIsBank = legateBankStr.equals("") ? false : Boolean.parseBoolean(legateBankStr);
                final int legateMoney = moneyLegateStr.equals("") ? 0 : Integer.parseInt(moneyLegateStr);
                final int minMoney = minMoneyLegateStr.equals("") ? 0 : Integer.parseInt(minMoneyLegateStr);

                ModifyMoneyCard legateMoneyCardModelMoneyCard = new ModifyMoneyCard(descriptionOnDrawLabel,descriptionOnUseLabel,legateMoney,legateIsBank,IsLegate,minMoney);
                CardView legateMoneyViewMoneyCard = new CardView();
                MoneyCardController legateMoneyControllerMoneyCard = new MoneyCardController(legateMoneyViewMoneyCard,legateMoneyCardModelMoneyCard);

                cards[cardsAdded] = legateMoneyControllerMoneyCard;
                cardsAdded++;
                break;
            case "modifyMoneyCard":
                final String moneyCardStr = tag.getAttribute("money");
                final String bankMoneyCardStr = tag.getAttribute("isBank"); //tilf'jet
                boolean isBankMoneyCard = bankMoneyCardStr.equals("") ? false : Boolean.parseBoolean(bankMoneyCardStr);
                final int moneyMoneyCard = moneyCardStr.equals("") ? 0 : Integer.parseInt(moneyCardStr);

                ModifyMoneyCard moneyCardModelMoneyCard = new ModifyMoneyCard(descriptionOnDrawLabel,descriptionOnUseLabel, moneyMoneyCard,isBankMoneyCard);
                CardView moneyViewMoneyCard = new CardView();
                MoneyCardController moneyControllerMoneyCard = new MoneyCardController(moneyViewMoneyCard,moneyCardModelMoneyCard);

                cards[cardsAdded] = moneyControllerMoneyCard;
                cardsAdded++;
                break;
            case "moveToNearestCard":
                String[] squareIDs = tag.getAttribute("squareID").split(" ");
                int[] ids = new int[squareIDs.length];
                for (int i = 0; i < ids.length; i++) {
                    ids[i] = Integer.parseInt(squareIDs[i]);
                }

                MoveToNearestCard nearestCard = new MoveToNearestCard(descriptionOnDrawLabel,descriptionOnUseLabel,IDSquares);
                CardView nearestView = new CardView();
                MoveToNearestCardController moveNearestControllerCard = new MoveToNearestCardController(nearestView,nearestCard);
                cards[cardsAdded] = moveNearestControllerCard;
                cardsAdded++;
                break;
            case "moveRelativeCard":
                final String moveSquaresStr = tag.getAttribute("moveSquares");
                final int moveSquares = moveSquaresStr.equals("") ? 0 : Integer.parseInt(moveSquaresStr);


                MoveRelativeCard relativeCard = new MoveRelativeCard(descriptionOnDrawLabel, descriptionOnUseLabel, moveSquares);
                CardView relativeView = new CardView();
                MoveToRelativeCardController moveRelativeControllerCard = new MoveToRelativeCardController(relativeView,relativeCard);

                cards[cardsAdded] = moveRelativeControllerCard;
                cardsAdded++;
                break;
            case "pardonCard":
                PardonCard pardonCard = new PardonCard(descriptionOnDrawLabel,descriptionOnUseLabel);
                CardView pardonView = new CardView();
                PardonCardController pardonController = new PardonCardController(pardonView, pardonCard);

                cards[cardsAdded] = pardonController;
                cardsAdded++;
                break;
            case "prisonCard":
                final String prisonSquareIDStr = tag.getAttribute("squareID"); // 11 (prison square)
                final int prisonSquareID = prisonSquareIDStr.equals("") ? 0 : Integer.parseInt(prisonSquareIDStr);

                TeleportToNearestCard prisonCard = new TeleportToNearestCard(descriptionOnDrawLabel,descriptionOnDrawLabel,prisonSquareID); // model
                CardView prisonView = new CardView(); // view
                TeleportToNearestCardController prisonController = new TeleportToNearestCardController(prisonView,prisonCard); // controller

                cards[cardsAdded] = prisonController;
                cardsAdded++;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elementName);
        }
    }


}
