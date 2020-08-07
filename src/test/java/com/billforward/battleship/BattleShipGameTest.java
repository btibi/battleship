package com.billforward.battleship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class BattleShipGameTest {

    @Test
    public void initTheGameAndCheckTheResultReturnEmptyList() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        assertTrue(battleShipGame.getCurrentState().isEmpty());
    }

    @Test
    public void ignoreTheInvalidCoordination() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(100, 100), Orientation.N);
        battleShipGame.addShips(Coordinates.of(-1, -1), Orientation.N);

        assertTrue(battleShipGame.getCurrentState().isEmpty());
    }

    @Test
    public void whenWeAddShipAndTheCellIsOccupiedThenShipIsSunkAndTheCellCanBeOccupiedByAnotherShip() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.E);

        assertEquals(Arrays.asList("(0, 0, E)", "(0, 0, N) SUNK"), battleShipGame.getCurrentState());
    }

    @Test
    public void whenWeMoveShipAndTheCellIsOccupiedThenShipIsSunkAndTheCellCanBeOccupiedByAnotherShip() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.addShips(Coordinates.of(0, 2), Orientation.S);
        battleShipGame.moveShip(Coordinates.of(0, 2), "MM");

        assertEquals(Arrays.asList("(0, 0, S)", "(0, 0, N) SUNK"), battleShipGame.getCurrentState());
    }

    @Test
    public void whenWeMoveShipAndTheCellIsOccupiedThenShipIsSunkAndTheCellCanBeOccupiedByAnotherShip2() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.addShips(Coordinates.of(0, 2), Orientation.S);
        battleShipGame.moveShip(Coordinates.of(0, 2), "M");
        battleShipGame.moveShip(Coordinates.of(0, 0), "M");

        assertEquals(Arrays.asList("(0, 1, N)", "(0, 1, S) SUNK"), battleShipGame.getCurrentState());
    }

    @Test
    public void whenShipMoveOutFromTheBoard() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.moveShip(Coordinates.of(0, 0), "MMMLM");

        assertEquals(Collections.singletonList("(0, 3, W) SUNK"), battleShipGame.getCurrentState());
    }

    @Test
    public void ignoreInvalidShipMoveCoordinates() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.moveShip(Coordinates.of(100, 100), "MMMLM");
        battleShipGame.moveShip(Coordinates.of(-1, -1), "MMMLM");

        assertEquals(Collections.singletonList("(0, 0, N)"), battleShipGame.getCurrentState());
    }

    @Test
    public void ignoreInvalidMovements() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.moveShip(Coordinates.of(0, 0), "MMMRASSFDSMRMMMLMRRR");

        assertEquals(Collections.singletonList("(2, 0, N)"), battleShipGame.getCurrentState());
    }

    @Test
    public void ignoreInvalidShoot() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.shoot(Coordinates.of(100, 1000));

        assertEquals(Collections.singletonList("(0, 0, N)"), battleShipGame.getCurrentState());
    }

    @Test
    public void canNotMoveShipAfterShoot() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.shoot(Coordinates.of(0, 0));
        battleShipGame.moveShip(Coordinates.of(0, 0), "MMM");

        assertEquals(Collections.singletonList("(0, 0, N) SUNK"), battleShipGame.getCurrentState());
    }

    @Test
    public void testExampleGame() {
        BattleShipGame battleShipGame = new BattleShipGame(10);

        battleShipGame.addShips(Coordinates.of(0, 0), Orientation.N);
        battleShipGame.addShips(Coordinates.of(9, 2), Orientation.E);
        battleShipGame.moveShip(Coordinates.of(0, 0), "MRMLMM");
        battleShipGame.shoot(Coordinates.of(9, 2));

        assertEquals(Arrays.asList("(1, 3, N)", "(9, 2, E) SUNK"), battleShipGame.getCurrentState());
    }
}