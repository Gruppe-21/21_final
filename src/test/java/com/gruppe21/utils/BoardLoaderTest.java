package com.gruppe21.utils;

import com.gruppe21.game.board.squares.Square;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardLoaderTest {

    @Test
    public void canLoadFile() {
        String filename = "test_board";
        List<Square> list = null;
        try {
            list = BoardLoader.loadBoard(filename);
        } catch (ParserConfigurationException e) {
            list = null;
        } catch (IOException e) {
            list = null;

        } catch (SAXException e) {
            list = null;
        }

        for (Square square : list) {
            System.out.println(square.getName());
        }

        assertEquals(false, list == null);
    }
}