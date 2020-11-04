package com.gruppe21.utils;

import com.gruppe21.Square;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BoardLoader {

    public static ArrayList<String> loadBoardFromFile(String fileName) throws ParserConfigurationException, IOException, SAXException {
        NodeList nList = getBoardNodeList(fileName);

        ArrayList<Square> squares = new ArrayList<Square>();
        ArrayList<String> squareNames = new ArrayList<String>();

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                addXMLSquareToArrayList(squareNames, eElement);
            }

        }

        for (String squareName : squareNames) {
            System.out.println(squareName);
        }

        return squareNames;
    }

    private static void addXMLSquareToArrayList(ArrayList<String> squareNames, Element eElement) {
        String elementName = eElement.getNodeName();
        switch (elementName) {
            case "StartSquare":
                // Add square
                squareNames.add("Start");
                break;
            case "PropertySquare":
                // Add square
                squareNames.add(elementName + ": " + eElement.getAttribute("name") + " - " + eElement.getAttribute("price"));
                break;
            case "ChanceSquare":
                // Add square
                squareNames.add("Chance");
                break;
            case "FreeParkingSquare":
                // Add square
                squareNames.add("Free Parking");

                break;
            case "GoToPrisonSquare":
                // Add square
                squareNames.add("Go To Prison");

                break;
            case "PrisonSquare":
                // Add square
                squareNames.add("Prison / Visit Prison");

                break;
            default:
                break;

        }
    }

    private static NodeList getBoardNodeList(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        String charSetName = "UTF-8";
        InputStream inputStream = BoardLoader.class.getResourceAsStream("/" + fileName + ".xml");
        Document doc = builder.parse(inputStream);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("board").item(0).getChildNodes();
        return nList;
    }
}
