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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Localisation {

    private static Localisation instance;

    private static  final String LANG_DIRECTORY = "/lang/";
    private static  final String TAG_LOCALE = "locale";
    private static  final String ATTRIBUTE_LANGUAGE_LOCALE = "lang";
    private static  final String ATTRIBUTE_LANGUAGE_NAME = "name";
    private static final String  START_LOCALE = "da_DK";

    private static  final String TAG_SENTENCE = "sentence";
    private static  final String ATTRIBUTE_LABEL = "label";

    private String currentLocale;

    public static Localisation getInstance() {
        if (instance == null)
            instance = new Localisation();
        return instance;
    }

    public Localisation(){
        currentLocale = START_LOCALE;
    }

    public String[] getAllLocales() {
        ArrayList<String> localeNames = new ArrayList<>(); // HUSK: ingen ArrayList
        InputStream in = getClass().getResourceAsStream(LANG_DIRECTORY);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String resource;

            while ((resource = br.readLine()) != null) {
                String locale = resource.split("\\.")[0];
                Document document = XMLUtil.getXMLDocument(LANG_DIRECTORY + locale);
                Node rootNode = XMLUtil.getRootNode(document);
                Element tag = (Element) rootNode;
                localeNames.add(tag.getAttribute(ATTRIBUTE_LANGUAGE_LOCALE) + " " + tag.getAttribute(ATTRIBUTE_LANGUAGE_NAME));
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
            Document document = XMLUtil.getXMLDocument(LANG_DIRECTORY + locale);
            Node rootNode = XMLUtil.getRootNode(document);

            if (rootNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tag = (Element) rootNode;
                if (tag.getTagName().equals(TAG_LOCALE) && tag.getAttribute(ATTRIBUTE_LANGUAGE_LOCALE).equals(currentLocale)) {
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
                    if (tag.getTagName().equals(TAG_SENTENCE) && tag.getAttribute(ATTRIBUTE_LABEL).equals(label)) {
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
        Pattern pattern = Pattern.compile("�\\[.*?]");
        for (int i = 0; i < variables.length; i++) {
            Matcher matcher = pattern.matcher(localisedText);
            if(!matcher.find()) break;
            //localisedText = localisedText.replaceAll(localisedText.substring(matcher.start(), matcher.end()).replaceFirst("\\[","\\\\["), variables[i]);
            localisedText = localisedText.replaceAll(matcher.group(0).replaceFirst("\\[","\\\\["), variables[i]);
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
