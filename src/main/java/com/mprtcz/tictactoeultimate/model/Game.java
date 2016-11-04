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

        FieldState(String value, int number) {
            this.value = value;
            this.number = number;
        }

        public abstract FieldState getOpposite();
    }

    private FieldState[][] table;
    private FieldState currentPlayer;

    private int[] sumX;
    private int[] sumY;
    private int[] sumZ;

    private Game(int tableSize) {
        this.table = new FieldState[tableSize][tableSize];
        currentPlayer = FieldState.O;
        sumX = new int[tableSize];
        sumY = new int[tableSize];
        sumZ  = new int[2];
        init();
    }

    private void init() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = FieldState.EMPTY;
            }
        }
    }

    private boolean addToCross(int indexX, int indexY) {
        if (indexX == indexY) {
            sumZ[0] += currentPlayer.getNumber();
            isLineWinning(sumZ[0]);
        }
        if ((indexX + indexY) == (table.length - 1)) {
            if (table[indexX][indexY].getNumber() != 0) {
                sumZ[1] += currentPlayer.getNumber();
            }
            isLineWinning(sumZ[1]);
        }
        return isLineWinning(sumZ[0]) || isLineWinning(sumZ[1]);
    }

    private boolean addToLinesSums(int indexX, int indexY) {
        sumX[indexX] += currentPlayer.getNumber();
        sumY[indexY] += currentPlayer.getNumber();
        return (isLineWinning(sumY[indexY]) || isLineWinning(sumX[indexX]));
    }

    private boolean isLineWinning(int lineSum) {
        return lineSum == (table.length * currentPlayer.getNumber());
    }

    private boolean addToSums(int indexX, int indexY) {
        return addToLinesSums(indexX, indexY) || addToCross(indexX, indexY);
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
        Game game = new Game(3);
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
