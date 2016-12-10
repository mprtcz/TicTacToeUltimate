package com.mprtcz.tictactoeultimate.service;

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

    public void createGame(Principal principal) {
        Game game = new Game(CUSTOM_TABLE_SIZE, principal);
        gamesList.add(game);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public String findGameAndInsertMove(Principal principal, String gameMove) {
        for (Game g : getGamesList()) {
            if (g.isPlayerInGame(principal)) {
                return g.makeAMove(principal.getName(), gameMove);
            }
        }
        return "Game with this host does not exist";
    }

    public String joinGame(String gameHost, Principal principal) {
        for (Game g : getGamesList()) {
            if (g.getGameHost().equals(gameHost)) {
                if (g.getSecondPlayer() == null) {
                    g.setSecondPlayer(principal);
                    return "Player " +principal.getName() +" joined game";
                } else {
                    return "The game already has second player";
                }
            }
        }
        return "Game with this host does not exist";
    }

}
