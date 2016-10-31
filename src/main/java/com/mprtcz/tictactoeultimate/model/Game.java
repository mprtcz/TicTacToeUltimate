package com.mprtcz.tictactoeultimate.model;

import lombok.Getter;

import java.util.*;

/**
 * Created by Azet on 2016-10-31.
 */
public class Game {

    @Getter
    public enum FieldState {
        X("X"),
        O("O"),
        EMPTY("-");

        private String value;

        FieldState(String value) {
            this.value = value;
        }
    }

    private FieldState[] table;
    private FieldState currentPlayer;

    public Game(int tableSize) {
        this.table = new FieldState[tableSize];
        currentPlayer = FieldState.O;
        init();
    }

    public void init() {
        for (int i = 0; i < table.length; i++) {
            table[i] = FieldState.EMPTY;
        }
    }

    public FieldState whoWins() {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>(Arrays.asList(0, 1, 2)));
        lists.add(new ArrayList<>(Arrays.asList(3, 4, 5)));
        lists.add(new ArrayList<>(Arrays.asList(6, 7, 8)));
        lists.add(new ArrayList<>(Arrays.asList(0, 3, 6)));
        lists.add(new ArrayList<>(Arrays.asList(1, 4, 7)));
        lists.add(new ArrayList<>(Arrays.asList(2, 5, 8)));
        lists.add(new ArrayList<>(Arrays.asList(0, 4, 8)));
        lists.add(new ArrayList<>(Arrays.asList(2, 4, 6)));
        for (List<Integer> list : lists) {
            if (areFieldsEqual(list)) {
                System.out.println("The winner is " +table[list.get(0)].value);
                return table[list.get(0)];
            }
        }
        return FieldState.EMPTY;
    }

    private boolean areFieldsEqual(List<Integer> indexesToCompare) {
        if ((table[indexesToCompare.get(0)] == FieldState.EMPTY) || (indexesToCompare.size() < 2)) {
            return false;
        }
        FieldState initialState = table[indexesToCompare.get(0)];
        for (int index :
                indexesToCompare) {
            if (table[index] != initialState) {
                return false;
            }
        }
        return true;
    }

    public FieldState changeSign() {
        if (currentPlayer == FieldState.O) {
            return FieldState.X;
        } else if (currentPlayer == FieldState.X) {
            return FieldState.O;
        } else {
            return FieldState.EMPTY;
        }
    }

    public void displayBoard(int divisor) {
        for (int i = 0; i < table.length; i++) {
            if (i % divisor == 0) {
                System.out.print("\n");
            }
            System.out.print(" " + table[i].value);
        }
        System.out.println("");
    }

    public void insertSymbol(int index) {
        if((index > table.length - 1)  || (index < 0)) {
            System.out.println("Index exceeds table size");
            return;
        }
        if(FieldState.EMPTY != table[index]) {
            System.out.println("The field is already picked, try again");
            return;
        }
        table[index] = currentPlayer;
        currentPlayer = changeSign();

    }

    public static void main(String[] args) {
        Game game = new Game(9);
        game.displayBoard(3);

        String inputLine;
        while (game.whoWins() == FieldState.EMPTY) {
            System.out.println("Type where you want to put " + game.currentPlayer);
            Scanner in = new Scanner(System.in);
            inputLine = in.nextLine();
            if(inputLine.toLowerCase().equals("exit")) {
                break;
            }
            try {
                int index = Integer.parseInt(inputLine);
                game.insertSymbol(index);
                game.displayBoard(3);
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            }

        }
    }
}
