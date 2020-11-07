package com.gruppe21.utils.localisation;

import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Localisation {

    private static Localisation instance;
    private final String sentenceTagName = "sentence";
    private String currentLocale = "en_US";

    public static Localisation getInstance() {
        if (instance == null)
            instance = new Localisation();
        return instance;
    }

    private NodeList getLocale(String locale) {
        try {
            String filePath = "/lang/" + "lang";
            Document document = XMLUtil.getXMLDocument(filePath);
            NodeList localesList = XMLUtil.getNodeListFromTag(document, "locales");

            if (localesList != null) {
                for (int i = 0; i < localesList.getLength(); i++) {
                    Node node = localesList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element tag = (Element) node;

                        if (tag.getTagName().equals("locale") && tag.getAttribute("lang").equals(currentLocale)) {
                            return tag.getChildNodes();
                        }
                    }
                }
            } else {
                return null;
            }
            return null;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getWordFromLocale(NodeList localeWordList, String label) {
        if (localeWordList != null) {
            for (int i = 0; i < localeWordList.getLength(); i++) {
                Node node = localeWordList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element tag = (Element) node;
                    if (tag.getTagName().equals("sentence") && tag.getAttribute("label").equals(label)) {
                        return tag.getTextContent();
                    }
                }
            }
        }
        return "";
    }

    public String getStringValue(String label) {
        NodeList locale = getLocale(currentLocale);
        if (locale != null) {
            return getWordFromLocale(locale, label);
        }
        return "Not defined";
    }

    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {

        this.currentLocale = currentLocale;
    }
}
