package com.mprtcz.tictactoeultimate.game.service;

import com.mprtcz.tictactoeultimate.configuration.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.game.model.Game;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mprtcz on 2016-11-22.
 */
@Service("gameService")
public class GameService {
    private static int CUSTOM_TABLE_SIZE = 3;
    private static List<Game> gamesList = new ArrayList<>();

    public ServerMessages createGame(Principal principal) {
        Game existingGameWithHost = findGameByHost(principal.getName());
        if(existingGameWithHost != null) {
            System.out.println("existingGameWithHost = " + existingGameWithHost);
            gamesList.remove(existingGameWithHost);
            System.out.println("gameslist after removal " + gamesList.toString());
        }

        Game game = new Game(CUSTOM_TABLE_SIZE, principal);
        gamesList.add(game);
        return new ServerMessages(ServerMessages.ServerMessageEnum.GAME_CREATED);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public ServerMessages findGameAndInsertMove(Principal principal, String gameMove) {
        for (Game g : filterUnresolvedGames()) {
            if (g.canAMoveBeMade(principal)) {
                return g.makeAMove(principal.getName(), gameMove);
            }
        }
        return new ServerMessages(ServerMessages.ServerMessageEnum.HOSTGAME_DOES_NOT_EXIST);
    }

    public ServerMessages terminateGame(String gameHost) {
        getGamesList().remove(findGameByHost(gameHost));
        return new ServerMessages(ServerMessages.ServerMessageEnum.HOSTGAME_DOES_NOT_EXIST);
    }

    public ServerMessages joinGame(String gameHost, Principal principal) {
        for (Game g : filterUnresolvedGames()) {
            if (g.getGameHost().equals(gameHost)) {
                if (g.getSecondPlayer() == null) {
                    g.setSecondPlayer(principal);
                    return new ServerMessages(ServerMessages.ServerMessageEnum.OK,
                            "Player " + principal.getName() + " joined game");
                } else {
                    return new ServerMessages(ServerMessages.ServerMessageEnum.SECOND_PLAYER_EXISTS);
                }
            }
        }
        return new ServerMessages(ServerMessages.ServerMessageEnum.HOSTGAME_DOES_NOT_EXIST);
    }

    public Game getGameUpdate(String gameHost, Principal principal) {
        System.out.println("gamesList : " + gamesList.toString());
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost)) {
                if (checkIfPlayerIsInTheGame(g, principal)) {
                    return g;
                }
            }
        }
        return null;
    }

    private boolean checkIfPlayerIsInTheGame(Game g, Principal principal) {
        return g.getGameHost().equals(principal.getName()) || g.getSecondPlayer() != null && g.getSecondPlayer().equals(principal.getName());
    }

    private Game findGameByHost(String gameHost) {
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost)) {
                return g;
            }
        }
        return null;
    }

    private Game findUnresolvedGameByHost(String gameHost) {
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost) && g.isUnresolved()) {
                return g;
            }
        }
        return null;
    }

    public List<Game> filterUnresolvedGames() {
        return getGamesList().stream().filter(Game::isUnresolved).collect(Collectors.toList());
    }

}
