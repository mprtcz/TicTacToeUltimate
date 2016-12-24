package com.mprtcz.tictactoeultimate.game.model.interfaces;

import com.mprtcz.tictactoeultimate.configuration.messages.ServerMessages;

import java.security.Principal;

/**
 * Created by mprtcz on 2016-12-24.
 */
public interface Game {

    void setGameId(Long gameId);

    void setSecondPlayer(Principal secondPlayer);

    ServerMessages makeAMove(String username, String position);

    boolean canAMoveBeMade(Principal principal);

    boolean isUnresolved();

    String getGameHost();

    String getSecondPlayer();

    String getSymbolAtCoordinates(String move);

    Long getGameId();
}
