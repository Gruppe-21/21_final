package com.gruppe21.utils.xmlutils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class XMLUtil {

    public static NodeList getNodeListFromTag(Document doc, String tagName) {
        return doc.getElementsByTagName(tagName).item(0).getChildNodes();
    }

    public static Node getRootNode(Document doc) {
        return doc.getDocumentElement();
    }

    public static Document getXMLDocument(String fileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = XMLUtil.class.getResourceAsStream(fileName + ".xml");
        Document doc = builder.parse(inputStream);
        doc.getDocumentElement().normalize();
        return doc;
    }

}
