package com.gruppe21.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Localisation {

    // Singleton pattern
    private static Localisation instance;

    public static Localisation getInstance() {
        if (instance == null)
            instance = new Localisation();
        return instance;
    }

    public String getStringValue(String lang, String label) {
        try {

            String filePath = "/lang/" + lang + ".txt";
            String charSetName = "UTF-8";
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSetName);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Iterate until no lines.
            while (true) {

                String line = bufferedReader.readLine();
                if (line == null)
                    break;

                String[] words = line.split(" ");

                // If label was found, return localized string
                if (words[0].equals(label)) {
                    words[0] = "";
                    StringBuilder localizedString = new StringBuilder();

                    for (String word : words) {
                        localizedString.append(word).append(" ");
                    }
                    return localizedString.toString().trim();
                }
            }
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
