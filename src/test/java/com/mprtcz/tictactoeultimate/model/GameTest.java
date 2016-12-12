package com.mprtcz.tictactoeultimate.model;

import com.mprtcz.tictactoeultimate.messages.ServerMessages;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mprtcz on 2016-11-06.
 */
public class GameTest {
    private static final int BOARD_SIDE_SIZE = 3;
    private int[][] O_WINNING_SIZE_3_COORDS_HORIZONTAL = {{0,0}, {1,1}, {0,1}, {1,0}, {0,2}};
    private int[][] X_WINNING_SIZE_3_COORDS_VERTICAL = {{0,0}, {0,1}, {1,0}, {1,1}, {0,2}, {2,1}};
    private int[][] O_WINNING_SIZE_3_COORDS_FORWARDSLASH = {{0,2}, {0,1}, {1,1}, {1,0}, {2,0}};
    private int[][] X_WINNING_SIZE_3_COORDS_BACKSLASH = {{0,2}, {0,0}, {1,0}, {1,1}, {0,1}, {2,2}};

    @Test
    public void  insertSymbolTest_WinnerO_horizontal() {
        checkWinningConditions(BOARD_SIDE_SIZE, O_WINNING_SIZE_3_COORDS_HORIZONTAL, Game.FieldState.O);
    }

    @Test
    public void  insertSymbolTest_WinnerX_vertical() {
        checkWinningConditions(BOARD_SIDE_SIZE, X_WINNING_SIZE_3_COORDS_VERTICAL, Game.FieldState.X);
    }

    @Test
    public void  insertSymbolTest_WinnerO_forwardslash() {
        checkWinningConditions(BOARD_SIDE_SIZE, O_WINNING_SIZE_3_COORDS_FORWARDSLASH, Game.FieldState.O);
    }

    @Test
    public void  insertSymbolTest_WinnerX_backslash() {
        checkWinningConditions(BOARD_SIDE_SIZE, X_WINNING_SIZE_3_COORDS_BACKSLASH, Game.FieldState.X);
    }


    private void checkWinningConditions(int boardSize, int[][] coordinatesTable, Game.FieldState winningSign) {
        Game game = new Game(boardSize);
        ServerMessages isWinner = new ServerMessages(ServerMessages.ServerMessageEnum.GAME_CREATED);
        for (int[] coordinates :
                coordinatesTable) {
            assertNotEquals(isWinner, ServerMessages.ServerMessageEnum.GAME_IS_WON);
            isWinner = game.insertSymbol(coordinates[0], coordinates[1]);
        }
        assertEquals(isWinner.getMessageEnum(), ServerMessages.ServerMessageEnum.GAME_IS_WON);
        assertEquals(winningSign, game.getCurrentPlayer());
    }
}