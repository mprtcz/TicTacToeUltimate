package com.mprtcz.tictactoeultimate.service;

import com.mprtcz.tictactoeultimate.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.model.Game;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-22.
 */
@Service("gameService")
public class GameService {
    private static int CUSTOM_TABLE_SIZE = 3;
    private static List<Game> gamesList = new ArrayList<>();

    public ServerMessages createGame(Principal principal) {
        Game game = new Game(CUSTOM_TABLE_SIZE, principal);
        gamesList.add(game);
        return new ServerMessages(ServerMessages.ServerMessageEnum.GAME_CREATED);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public ServerMessages findGameAndInsertMove(Principal principal, String gameMove) {
        for (Game g : getGamesList()) {
            if (g.canAMoveBeMade(principal)) {
                return g.makeAMove(principal.getName(), gameMove);
            }
        }
        return new ServerMessages(ServerMessages.ServerMessageEnum.HOSTGAME_DOES_NOT_EXIST);
    }

    public ServerMessages joinGame(String gameHost, Principal principal) {
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost)) {
                if (g.getSecondPlayer() == null) {
                    g.setSecondPlayer(principal);
                    return new ServerMessages(ServerMessages.ServerMessageEnum.OK,
                            "Player " +principal.getName() +" joined game");
                } else {
                    return new ServerMessages(ServerMessages.ServerMessageEnum.SECOND_PLAYER_EXISTS);
                }
            }
        }
        return new ServerMessages(ServerMessages.ServerMessageEnum.HOSTGAME_DOES_NOT_EXIST);
    }

    public Game getGameUpdate(String gameHost, Principal principal) {
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost)) {
                if(checkIfPlayerIsInTheGame(g, principal)) {
                    return g;
                }
            }
        }
        return null;
    }

    private boolean checkIfPlayerIsInTheGame(Game g, Principal principal) {
        return g.getGameHost().equals(principal.getName()) || g.getSecondPlayer() != null && g.getSecondPlayer().equals(principal.getName());
    }

}
