package com.billforward.battleship;

import static org.junit.Assert.assertEquals;

import static com.billforward.battleship.Orientation.E;
import static com.billforward.battleship.Orientation.N;
import static com.billforward.battleship.Orientation.S;
import static com.billforward.battleship.Orientation.W;

import org.junit.Test;

public class OrientationTest {

    @Test
    public void turnLeft() {
        assertEquals(E, S.turn('L'));
        assertEquals(N, E.turn('L'));
        assertEquals(W, N.turn('L'));
        assertEquals(S, W.turn('L'));
    }

    @Test
    public void turnRight() {
        assertEquals(W, S.turn('R'));
        assertEquals(S, E.turn('R'));
        assertEquals(E, N.turn('R'));
        assertEquals(N, W.turn('R'));
    }

    @Test
    public void turnUnknownDoNotTurn() {
        assertEquals(S, S.turn('A'));
        assertEquals(E, E.turn('A'));
        assertEquals(N, N.turn('A'));
        assertEquals(W, W.turn('A'));
    }

    @Test
    public void calculateCoordinates() {
        assertEquals(Coordinates.of(5, 4), S.calculateCoordinates(Coordinates.of(5, 5)));
        assertEquals(Coordinates.of(5, 6), N.calculateCoordinates(Coordinates.of(5, 5)));
        assertEquals(Coordinates.of(6, 5), E.calculateCoordinates(Coordinates.of(5, 5)));
        assertEquals(Coordinates.of(4, 5), W.calculateCoordinates(Coordinates.of(5, 5)));
    }
}