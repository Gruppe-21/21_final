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

    // Henter kort fra XML
    public static OurArrayList<Card> loadCards(String fileName) throws ParserConfigurationException, IOException, SAXException {
        Document document = XMLUtil.getXMLDocument(CARD_DIRECTORY + fileName); // Finder mappen /cards/ i resources
        NodeList cardNodes = XMLUtil.getNodeListFromTag(document, TAG_CARD); // Leder efter root-tag i /cards/ mappen

        return getCardsFromNodeList(cardNodes); // Return alt indhold af root-tag (cards)
    }

    // Kører igennem alle child tags af root-tag (cards)
    private static OurArrayList<Card> getCardsFromNodeList(NodeList boardNodes) {
        OurArrayList<Card> chanceCards = new OurArrayList<Card>();

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
    private static void addXMLChanceCardToArrayList(OurArrayList<Card> chanceCards, Element tag) {
        String elementName = tag.getNodeName();
        final String descriptionOnDrawLabel = tag.getAttribute("onDrawDescription"); // Gemmer onDraw for alle kort
        final String descriptionOnUseLabel = tag.getAttribute("onUseDescription"); // Gemmer onDraw for alle kort

        // Ud fra tag finder den forskellige oplysninger om kortene
        switch (elementName) {
            case "moveRelativecard":
                final String square_IDStr = tag.getAttribute("ID"); // Gemmer indholdet af ID-tag
                final int square_ID = square_IDStr.equals("") ? 0 : Integer.parseInt(square_IDStr); // Omdanner String til int
                //final MoveRelativeCard type = MoveRelativeCard.valueOf(tag.getAttribute("type"));

                Card cardModel = new MoveRelativeCard(descriptionOnDrawLabel,descriptionOnUseLabel,square_ID);
                CardView view = new CardView();
                CardController controller = new CardController(cardModel, view);

                // TO-DO tilføj controller til array, når det er blevet gjort muligt :)
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
