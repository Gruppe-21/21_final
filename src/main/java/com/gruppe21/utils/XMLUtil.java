package com.gruppe21.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLUtil {

    public static NodeList getNodeListFromTag(Document doc, String tagName) throws ParserConfigurationException, SAXException, IOException {
        return doc.getElementsByTagName(tagName).item(0).getChildNodes();
    }

    public static Document getXMLDocument(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        String charSetName = "UTF-8";
        InputStream inputStream = BoardLoader.class.getResourceAsStream("/boards/" + fileName + ".xml");
        Document doc = builder.parse(inputStream);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
