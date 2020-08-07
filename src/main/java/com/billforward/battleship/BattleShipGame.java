package com.billforward.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BattleShipGame {
    private final Orientation[][] board;
    private final List<String> sunk;

    public BattleShipGame(int boardSize) {
        this.board = new Orientation[boardSize][boardSize];
        this.sunk = new ArrayList<>();
    }

    public void addShips(Coordinates coordinates, Orientation orientation) {
        if (validCoordinates(coordinates)) {
            // If the cell is occupied, that ship is sunk
            shoot(coordinates);
            board[coordinates.getX()][coordinates.getY()] = orientation;
        }
    }

    public void moveShip(Coordinates coordinates, String movements) {
        if (validCoordinates(coordinates)) {
            if (board[coordinates.getX()][coordinates.getY()] != null) {
                for (char movement : movements.toCharArray()) {
                    coordinates = moveShipOneMovement(coordinates, movement);
                }
            }
        }
    }

    public void shoot(Coordinates coordinates) {
        if (validCoordinates(coordinates)) {
            Optional.ofNullable(board[coordinates.getX()][coordinates.getY()]).ifPresent(o -> {
                board[coordinates.getX()][coordinates.getY()] = null;
                sunk.add(String.format("(%s, %s, %s) SUNK", coordinates.getX(), coordinates.getY(), o.name()));
            });
        }
    }

    public List<String> getCurrentState() {
        List<String> state = new ArrayList<>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                Orientation o = board[x][y];
                if (o != null) {
                    state.add(String.format("(%s, %s, %s)", x, y, o.name()));
                }
            }
        }
        state.addAll(sunk);
        return state;
    }

    private Coordinates moveShipOneMovement(Coordinates coordinates, char movement) {
        Orientation orientation = board[coordinates.getX()][coordinates.getY()];
        if ('M' == movement) {
            coordinates = moveShipOneMovement(coordinates, orientation);
        } else {
            turnShip(coordinates, movement, orientation);
        }
        return coordinates;
    }

    private void turnShip(Coordinates coordinates, char movement, Orientation orientation) {
        board[coordinates.getX()][coordinates.getY()] = orientation.turn(movement);
    }

    private Coordinates moveShipOneMovement(Coordinates coordinates, Orientation orientation) {
        //new coordinates depend on the orientation
        Coordinates newCoordinates = orientation.calculateCoordinates(coordinates);
        if (validCoordinates(newCoordinates)) {
            //remove from the old place
            board[coordinates.getX()][coordinates.getY()] = null;
            coordinates = newCoordinates;
            addShips(coordinates, orientation);
        } else {
            //sunk the ship if want to leave the board
            shoot(coordinates);
        }
        return coordinates;
    }

    private boolean validCoordinates(Coordinates coordinates) {
        return coordinates.getX() < board.length && coordinates.getX() >= 0 && coordinates.getY() < board.length
                && coordinates.getY() >= 0;
    }
}
