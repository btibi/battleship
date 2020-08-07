package com.billforward.battleship;

import static java.lang.Integer.parseInt;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a helper class for the BattleShip Game which assume that all string is valid so it does not validate them.
 */
public class BattleShipGameRunnerHelper {

    public static BattleShipGame createNewGame(String firstLine) {
        return new BattleShipGame(parseInt(firstLine));
    }

    public static void addShips(BattleShipGame battleShipGame, String secondLine) {
        Pattern shipPatter = Pattern.compile("(\\d),.(\\d),.([NSWE])");
        Matcher shipMatcher = shipPatter.matcher(secondLine);

        while (shipMatcher.find()) {
            Coordinates coordinates = Coordinates.of(parseInt(shipMatcher.group(1)), parseInt(shipMatcher.group(2)));
            Orientation orientation = Orientation.valueOf(shipMatcher.group(3));
            battleShipGame.addShips(coordinates, orientation);
        }
    }

    public static void runGame(BattleShipGame battleShipGame, List<String> movementLines) {
        Pattern shipPatter = Pattern.compile("(\\d),.(\\d).(\\s([A-Z]*))?");
        movementLines.forEach(movementLine -> {
            Matcher shipMatcher = shipPatter.matcher(movementLine);
            if (shipMatcher.find()) {
                Coordinates coordinates = Coordinates.of(parseInt(shipMatcher.group(1)),
                        parseInt(shipMatcher.group(2)));
                if (shipMatcher.group(4) != null) {
                    battleShipGame.moveShip(coordinates, shipMatcher.group(4));
                } else {
                    battleShipGame.shoot(coordinates);
                }
            }
        });
    }

}
