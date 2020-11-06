package com.gruppe21.utils.stringutils;

import com.gruppe21.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* This is very simplistic, however it could easily be made more sophisticated */
public class RandomNameGenerator {

    /* Should be read from a file */
    private final static String[] names = new String[]{
            "Admiral Akbar", "Henning DiCaprio", "Paulo", "X Æ A-12", "John Cena", "John Smith",
            "Galadriel", "Elrond", "Gandalf the Grey", "Saruman the White", "Frodo Baggins", "Samwise Gamgee",
            "Bilbo Baggins"};

    /**
     * Is and should only be used by tests
     *
     * @return names
     */
    public static String[] getAllNames() {
        return names;
    }

    /**
     * Returns a random name not included in namesToAvoid
     *
     * @param namesToAvoid
     * @return
     */
    public static String GetNameDifferentFrom(String[] namesToAvoid) {
        List<String> tempNames = new ArrayList<>(Arrays.asList(names));

        while (true) {
            if (tempNames.isEmpty()) return null;
            String chosenName = tempNames.get((int) (Math.random() * tempNames.size()));
            boolean nameFound = true;
            for (String name : namesToAvoid) {
                if (chosenName.equals(name)) {
                    tempNames.remove(chosenName);
                    nameFound = false;
                    break;
                }
            }
            if (nameFound) return chosenName;
        }
    }

    public static String GetNameDifferentFrom(Player[] namesToAvoid) {
        List<String> actualNames = new ArrayList<>();
        for (int i = 0; i < namesToAvoid.length; i++) {
            if (namesToAvoid[i] != null && namesToAvoid[i].getName() != null) {
                if (!namesToAvoid[i].getName().isEmpty()) {
                    actualNames.add(namesToAvoid[i].getName());
                }
            }
        }
        return RandomNameGenerator.GetNameDifferentFrom(actualNames.toArray(new String[actualNames.size()]));
    }
}
