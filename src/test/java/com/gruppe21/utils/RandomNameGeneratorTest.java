package com.gruppe21.utils;

import com.gruppe21.Player;
import com.gruppe21.utils.stringutils.RandomNameGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RandomNameGeneratorTest {

    /*Add test to test if null is returned when RandomNameGenerator.getAllNames() i given as parameter*/

    @Test
    public void getNameDifferentFromStringsReturnDifferentStrings() {
        String[] allNames = RandomNameGenerator.getAllNames();
        String[] names = Arrays.copyOfRange(allNames, 1, allNames.length);
        Boolean onlyDifferent = true;
        for (int i = 0; i < 1000000; i++) {
            if (!RandomNameGenerator.GetNameDifferentFrom(names).equals(allNames[0])) {
                onlyDifferent = false;
                break;
            }
        }
        Assert.assertEquals(true, onlyDifferent);
    }

    @Test
    public void getNameDifferentFromPlayersReturnDifferentStrings() {
        String[] allNames = RandomNameGenerator.getAllNames();
        String[] names = Arrays.copyOfRange(allNames, 1, allNames.length);
        Player[] players = new Player[names.length];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(names[i]);
        }
        Boolean onlyDifferent = true;
        for (int i = 0; i < 1000000; i++) {
            if (!RandomNameGenerator.GetNameDifferentFrom(players).equals(allNames[0])) {
                onlyDifferent = false;
                break;
            }
        }
        Assert.assertEquals(true, onlyDifferent);
    }
}