package com.gruppe21.utils;

import com.gruppe21.squares.controllers.SquareController;
import com.gruppe21.squares.models.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BoardLoaderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void loadBoard() {
        String filename = "main_board";
        SquareController[] squares = null;
        try {
            squares = BoardLoader.loadBoard(filename);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        assertNotNull(squares);
    }
}