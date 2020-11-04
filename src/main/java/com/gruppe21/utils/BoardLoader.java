package com.gruppe21.utils;

import com.gruppe21.Square;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BoardLoader {

    public static int loadBoardFromFile(String fileName) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        String charSetName = "UTF-8";
        InputStream inputStream = BoardLoader.class.getResourceAsStream("/"+fileName + ".xml");
        Document doc = builder.parse(inputStream);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("board").item(0).getChildNodes();

        ArrayList<Square> squares = new ArrayList<Square>();
        ArrayList<String> squareNames = new ArrayList<String>();


        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

              String elementName = eElement.getNodeName();
              switch (elementName){
                  case "StartSquare":
                      // Add square
                      squareNames.add("Start" );
                      break;
                  case "PropertySquare":
                      // Add square
                      squareNames.add(elementName + ": " + eElement.getAttribute("name") + " - " + eElement.getAttribute("price"));
                      break;
                  case "ChanceSquare":
                      // Add square
                      squareNames.add("Chance" );
                      break;
                  case "FreeParkingSquare":
                      // Add square
                      squareNames.add("Free Parking" );

                      break;
                  case "GoToPrisonSquare":
                      // Add square
                      squareNames.add("Go To Prison" );

                      break;
                  case "PrisonSquare":
                      // Add square
                      squareNames.add("Prison / Visit Prison" );

                      break;
                  default:
                      break;

              }

            }

        }

        for (String squareName : squareNames) {
            System.out.println(squareName);
        }

        return 0;
    }
}
