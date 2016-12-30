package com.mprtcz.tictactoeultimate.game.model;

import com.mprtcz.tictactoeultimate.configuration.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.game.model.interfaces.Game;
import lombok.Getter;
import sun.security.acl.PrincipalImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by mprtcz on 2016-10-31.
 */
@Getter
public class TicTacToeGame implements Game {

    @Getter
    public enum FieldState {
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
    private Long gameId;

    private FieldState[][] table;
    private String[] oneDimTable;
    private FieldState currentPlayer;

    private String gameHost;
    private String secondPlayer;
    private String winner;

    private int[] horizontalSums;
    private int[] verticalSums;
    private int[] diagonalSums;

    public TicTacToeGame(int tableSize, Principal gameHost, Long gameId) {
        this.gameId = gameId;
        this.table = new FieldState[tableSize][tableSize];
        this.oneDimTable = new String[tableSize*tableSize];
        this.gameHost = gameHost.getName();
        this.currentPlayer = FieldState.O;
        this.horizontalSums = new int[tableSize];
        this.verticalSums = new int[tableSize];
        this.diagonalSums = new int[NUMBER_OF_DIAGONALS];
        init();
    }

    public TicTacToeGame(int tableSize) {
        this.table = new FieldState[tableSize][tableSize];
        this.oneDimTable = new String[tableSize*tableSize];
        this.currentPlayer = FieldState.O;
        this.horizontalSums = new int[tableSize];
        this.verticalSums = new int[tableSize];
        this.diagonalSums = new int[NUMBER_OF_DIAGONALS];
        init();
    }

    @Override
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    public void setSecondPlayer(Principal secondPlayer) {
        this.secondPlayer = secondPlayer.getName();
    }

    public ServerMessages insertSymbol(int indexVertical, int indexHorizontal) {
        if ((indexVertical > table[indexHorizontal].length - 1) || (indexVertical < 0)
                || (indexHorizontal > table.length - 1) || (indexHorizontal < 0)) {
            return new ServerMessages(ServerMessages.ServerMessageEnum.COORDINATES_OUT_OF_TABLE);
        }
        if (FieldState.EMPTY != table[indexHorizontal][indexVertical]) {
            return new ServerMessages(ServerMessages.ServerMessageEnum.SYMBOL_ALREADY_IN_PLACE);
        }
        table[indexHorizontal][indexVertical] = currentPlayer;
        oneDimTable[indexHorizontal * table.length + indexVertical] = currentPlayer.getValue();
        boolean isWinner = addToSums(indexHorizontal, indexVertical);
        if (isWinner) {
            this.winner = getCurrentPlayersName();
            return new ServerMessages(ServerMessages.ServerMessageEnum.GAME_IS_WON,
                    "The winner is: " + getCurrentPlayersName() + getBoardAsJson());
        }
        currentPlayer = currentPlayer.getOpposite();
        return new ServerMessages(ServerMessages.ServerMessageEnum.SUCCESSFUL_MOVE, getBoardAsJson());
    }

    @Override
    public ServerMessages makeAMove(String username, String position) {
        if(getCurrentPlayersName().equals(username)) {
            try {
                String[] indexes = position.split(",");
                if (indexes.length != 2) {
                    return new ServerMessages(ServerMessages.ServerMessageEnum.ONLY_NUMBERS);
                }
                return insertSymbol(Integer.parseInt(indexes[1]), Integer.parseInt(indexes[0]));
            } catch (NumberFormatException e) {
                return new ServerMessages(ServerMessages.ServerMessageEnum.ONLY_NUMBERS);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ServerMessages(ServerMessages.ServerMessageEnum.GENERIC_EXCEPTION, ex.toString());
            }
        }
        return new ServerMessages(ServerMessages.ServerMessageEnum.WRONG_PLAYER);
    }

    @Override
    public boolean canAMoveBeMade(Principal principal) {
        return this.winner == null && gameHost != null && secondPlayer != null && isPlayerInGame(principal);
    }

    @Override
    public boolean isUnresolved() {
        return this.winner == null;
    }

    @Override
    public String getSymbolAtCoordinates(String coordinates) {
        String[] indexes = coordinates.split(",");
        if (indexes.length != 2) {
            return null;
        }
        int xCoordinate = Integer.parseInt(indexes[0]);
        int yCoordinate = Integer.parseInt(indexes[1]);
        FieldState fieldState = table[xCoordinate][yCoordinate];
        return fieldState.getValue();
    }

    private String getCurrentPlayersName() {
        switch (currentPlayer.getValue()) {
            case "O":
                return gameHost;
            case "X":
                return secondPlayer;
            default:
                return "Unrecognized player";
        }
    }

    private void init() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = FieldState.EMPTY;
                oneDimTable[i*table.length + j] = " ";
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

    private void displayBoard() {
        for (FieldState[] tableLine : table) {
            for (FieldState fieldState : tableLine) {
                System.out.print(" " + fieldState.value);
            }
            System.out.println(" ");
        }
    }

    private String getBoardAsJson() {
        List<String> list = new ArrayList<>();
        for (FieldState[] row : table) {
            for (FieldState col : row) {
                list.add(col.value);
            }
        }
        return list.toString();
    }

    private boolean isPlayerInGame(Principal principal) {
        return gameHost.equals(principal.getName()) || secondPlayer.equals(principal.getName());
    }

    private boolean addToSums(int horizontalCoordinate, int verticalCoordinate) {
        return addToLinesSums(horizontalCoordinate, verticalCoordinate) || addToDiagonals(horizontalCoordinate, verticalCoordinate);
    }

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame(3, new PrincipalImpl("dummyUsername"), 1L);
        ticTacToeGame.displayBoard();

        ServerMessages isWinner = new ServerMessages(ServerMessages.ServerMessageEnum.GAME_CREATED);
        String inputLine;
        do {
            System.out.println("Type where you want to put " + ticTacToeGame.currentPlayer + ", separate coordinates with comma");
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
                isWinner = ticTacToeGame.insertSymbol(Integer.parseInt(indexes[1]), Integer.parseInt(indexes[0]));
                ticTacToeGame.displayBoard();
            } catch (NumberFormatException e) {
                System.out.println("Only numbers!");
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        while (isWinner.getMessageEnum() != ServerMessages.ServerMessageEnum.GAME_IS_WON);
        System.out.println("The winner is " + ticTacToeGame.currentPlayer);
    }
}
