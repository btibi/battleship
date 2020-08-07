package com.billforward.battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class BattleShipGameRunnerHelperTest {

    @Test
    public void createNewGameCreateEmptyGame() {
        BattleShipGame game = BattleShipGameRunnerHelper.createNewGame("10");

        assertTrue(game.getCurrentState().isEmpty());
    }

    @Test
    public void addShipsPareStringAndAddShipsIntoTheGame() {
        BattleShipGame game = BattleShipGameRunnerHelper.createNewGame("10");
        BattleShipGameRunnerHelper.addShips(game, "(0, 0, N) (9, 2, E)");

        assertEquals(Arrays.asList("(0, 0, N)", "(9, 2, E)"), game.getCurrentState());
    }

    @Test
    public void runGameParseStringAndProcessMovements() {
        BattleShipGame game = BattleShipGameRunnerHelper.createNewGame("10");
        BattleShipGameRunnerHelper.addShips(game, "(0, 0, N) (9, 2, E)");
        BattleShipGameRunnerHelper.runGame(game, Arrays.asList("(0, 0) MRMLMM", "(9, 2)"));

        assertEquals(Arrays.asList("(1, 3, N)", "(9, 2, E) SUNK"), game.getCurrentState());
    }
}