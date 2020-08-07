package com.billforward.battleship;

public enum Orientation {
    N,
    E,
    S,
    W;

    static private final Orientation[] values = values();

    public Orientation turn(char turn) {
        switch (turn) {
            case 'L':
                return left();
            case 'R':
                return right();
            default:
                return this;
        }
    }

    public Coordinates calculateCoordinates(Coordinates coordinates) {
        switch (this) {
            case E:
                return Coordinates.of(coordinates.getX() + 1, coordinates.getY());
            case W:
                return Coordinates.of(coordinates.getX() - 1, coordinates.getY());
            case N:
                return Coordinates.of(coordinates.getX(), coordinates.getY() + 1);
            case S:
                return Coordinates.of(coordinates.getX(), coordinates.getY() - 1);
            default:
                return coordinates;
        }
    }

    private Orientation right() {
        return values[(ordinal() + 1) % values.length];
    }

    private Orientation left() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }
}
