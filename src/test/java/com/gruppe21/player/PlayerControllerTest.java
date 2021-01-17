package com.gruppe21.player;

import com.gruppe21.gui.GUIManager;
import com.gruppe21.utils.CardLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    PlayerController player;

    @BeforeEach
    void setUp() {
        GUIManager.getInstance().enableTesting();
        player = new PlayerController();

    }

    @AfterEach
    void tearDown() {
        player = null;
        CardLoader.cardsAdded = 0;
    }

    @Test
    void takeTurn() {

    }

    @Test
    void teleportTo() {
    }

    @Test
    void moveTo() {
    }

    @Test
    void drawCard() {
    }

    @Test
    void transferMoney() {
    }

    @Test
    void liquidateAssets() {
    }

    @Test
    void testLiquidateAssets() {
    }

    @Test
    void purchaseProperty() {
    }

    @Test
    void addOwnedProperty() {
    }

    @Test
    void removeOwnedProperty() {
    }

    @Test
    void purchaseBuildings() {
    }

    @Test
    void payOffMortgages() {
    }

    @Test
    void addBalance() {
    }

    @Test
    void getTotalValue() {
    }

    @Test
    void getTotalNumberOfBuildings() {
    }

    @Test
    void getTotalNumberOfHouses() {
    }

    @Test
    void getTotalNumberOfHotels() {
    }

    @Test
    void isBankrupt() {
    }

    @Test
    void getLastRollForBrewery() {
    }
}