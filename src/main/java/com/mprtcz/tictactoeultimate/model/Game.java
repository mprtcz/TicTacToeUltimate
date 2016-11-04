package com.mprtcz.tictactoeultimate.model;

import lombok.Getter;

import java.util.Scanner;

/**
 * Created by Azet on 2016-10-31.
 */
public class Game {

    @Getter
    enum FieldState {
        X("X", 1) {
            public FieldState getOpposite() {
                return FieldState.O;
            }
        },
        O("O", -1) {
            public FieldState getOpposite() {
                return FieldState.X;
            }
        },
        EMPTY("-", 0) {
            public FieldState getOpposite() {
                return FieldState.EMPTY;
            }
        };

        private String value;
        private int number;

        FieldState(String representation, int value) {
            this.value = representation;
            this.number = value;
        }

        public abstract FieldState getOpposite();
    }

    private static final int NUMBER_OF_DIAGONALS = 2;

    private FieldState[][] table;
    private FieldState currentPlayer;

    private int[] horizontalSums;
    private int[] verticalSums;
    private int[] diagonalSums;

    private Game(int tableSize) {
        this.table = new FieldState[tableSize][tableSize];
        currentPlayer = FieldState.O;
        horizontalSums = new int[tableSize];
        verticalSums = new int[tableSize];
        diagonalSums = new int[NUMBER_OF_DIAGONALS];
        init();
    }

    private void init() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = FieldState.EMPTY;
            }
        }
    }

    private boolean addToDiagonals(int horizontalCoordinate, int verticalCoordinate) {
        if (horizontalCoordinate == verticalCoordinate) {
            diagonalSums[0] += currentPlayer.getNumber();
            isLineWinning(diagonalSums[0]);
        }
        if ((horizontalCoordinate + verticalCoordinate) == (table.length - 1)) {
            if (table[horizontalCoordinate][verticalCoordinate].getNumber() != 0) {
                diagonalSums[1] += currentPlayer.getNumber();
            }
            isLineWinning(diagonalSums[1]);
        }
        return isLineWinning(diagonalSums[0]) || isLineWinning(diagonalSums[1]);
    }

    private boolean addToLinesSums(int horizontalCoordinate, int verticalCoordinate) {
        horizontalSums[horizontalCoordinate] += currentPlayer.getNumber();
        verticalSums[verticalCoordinate] += currentPlayer.getNumber();
        return (isLineWinning(verticalSums[verticalCoordinate]) || isLineWinning(horizontalSums[horizontalCoordinate]));
    }

    private boolean isLineWinning(int lineSum) {
        return lineSum == (table.length * currentPlayer.getNumber());
    }

    private boolean addToSums(int horizontalCoordinate, int verticalCoordinate) {
        return addToLinesSums(horizontalCoordinate, verticalCoordinate) || addToDiagonals(horizontalCoordinate, verticalCoordinate);
    }

    private void displayBoard() {
        for (FieldState[] tableLine : table) {
            for (FieldState fieldState : tableLine) {
                System.out.print(" " + fieldState.value);
            }
            System.out.println(" ");
        }
    }

    private boolean insertSymbol(int indexVertical, int indexHorizontal) {
        if ((indexVertical > table[indexHorizontal].length - 1) || (indexVertical < 0)
                || (indexHorizontal > table.length - 1) || (indexHorizontal < 0)) {
            System.out.println("Index exceeds table size");
            return false;
        }
        if (FieldState.EMPTY != table[indexHorizontal][indexVertical]) {
            System.out.println("The field is already picked, try again");
            return false;
        }
        table[indexHorizontal][indexVertical] = currentPlayer;
        boolean isWinner = addToSums(indexHorizontal, indexVertical);
        if (isWinner) {
            return true;
        }
        currentPlayer = currentPlayer.getOpposite();
        return false;
    }

    public static void main(String[] args) {
        Game game = new Game(5);
        game.displayBoard();

        boolean isWinner = false;
        String inputLine;
        do {
            System.out.println("Type where you want to put " + game.currentPlayer + ", separate coordinates with comma");
            Scanner in = new Scanner(System.in);
            inputLine = in.nextLine();
            if (inputLine.toLowerCase().equals("exit")) {
                break;
            }
            try {
                String[] indexes = inputLine.split(",");
                if (indexes.length != 2) {
                    throw new Exception("Two numbers, babe");
                }
                isWinner = game.insertSymbol(Integer.parseInt(indexes[1]), Integer.parseInt(indexes[0]));
                game.displayBoard();
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        while (!isWinner);
        System.out.println("The winner is " + game.currentPlayer);
    }
}
