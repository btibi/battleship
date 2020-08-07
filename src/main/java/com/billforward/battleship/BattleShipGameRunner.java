package com.billforward.battleship;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BattleShipGameRunner {

    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];

        runGame(inputFilePath, outputFilePath);
    }

    private static void runGame(String inputFilePath, String outputFilePath) {
        //I assume that the file is always correct so  i don't do any validation
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            List<String> lines = stream.collect(Collectors.toList());

            if (lines.size() >= 2) {
                BattleShipGame battleShipGame = BattleShipGameRunnerHelper.createNewGame(lines.get(0));
                BattleShipGameRunnerHelper.addShips(battleShipGame, lines.get(1));
                BattleShipGameRunnerHelper.runGame(battleShipGame, lines.subList(2, lines.size()));

                //create file result
                Files.write(Paths.get(outputFilePath), String.join("\n", battleShipGame.getCurrentState()).getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
