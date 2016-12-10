package com.mprtcz.tictactoeultimate.service;

import lombok.Getter;

/**
 * Created by mprtcz on 2016-12-10.
 */
@Getter
public enum ServerMessages {
    SECOND_PLAYER_EXISTS("Second player already exists in this game"),
    OK("OK"),
    HOSTGAME_DOES_NOT_EXIST("game with this host does not exist"),
    GAME_DOES_NOT_EXIST("This game does not exist"),
    SUCCESSFUL_MOVE("Move has been successfully registered"),
    ONLY_NUMBERS("Move coordinates are bad"),
    GENERIC_EXCEPTION("Server has encountered an exception"),
    WRONG_PLAYER("Wrong player"),
    GAME_IS_WON("The game has a winner");


    private String exceptionValue;

    ServerMessages(String exceptionValue) {
        this.exceptionValue = exceptionValue;
    }
}
