package com.gruppe21.utils.localisation;

import com.gruppe21.utils.xmlutils.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Localisation {

    private static Localisation instance;
    private final String sentenceTagName = "sentence";
    private final String filePath = "/lang/";
    private String currentLocale = "en";

    public static Localisation getInstance() {
        if (instance == null)
            instance = new Localisation();
        return instance;
    }


    public String[] getAllLocales() {
        ArrayList<String> localeNames = new ArrayList<>();
        InputStream in = getClass().getResourceAsStream("/lang/");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String resource;

            while ((resource = br.readLine()) != null) {
                String locale = resource.split("\\.")[0];
                Document document = XMLUtil.getXMLDocument(filePath + locale);
                Node rootNode = XMLUtil.getRootNode(document);
                Element tag = (Element) rootNode;
                localeNames.add(tag.getAttribute("lang") + " " + tag.getAttribute("name"));
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        localeNames.removeAll(Arrays.asList("", null));
        localeNames.removeAll(Arrays.asList(" ", null));
        return localeNames.toArray(new String[0]);

    }

    private NodeList getLocale(String locale) {
        try {
            Document document = XMLUtil.getXMLDocument(filePath + locale);
            Node rootNode = XMLUtil.getRootNode(document);

            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) rootNode;
                if (tag.getTagName().equals("locale") && tag.getAttribute("lang").equals(currentLocale)) {
                    return tag.getChildNodes();
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
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


    public String getStringValue(String label, String... variables) {
        String localisedText = getStringValue(label);
        for (int i = 0; i < variables.length; i++) {
            localisedText.replaceFirst("�\\[(.*?)]", variables[i]);
        }
        return localisedText;
    }


    public String getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(String currentLocale) {

        this.currentLocale = currentLocale;
    }
}
