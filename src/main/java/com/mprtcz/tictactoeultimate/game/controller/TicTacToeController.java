package com.mprtcz.tictactoeultimate.game.controller;

import com.mprtcz.tictactoeultimate.configuration.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.game.model.interfaces.Game;
import com.mprtcz.tictactoeultimate.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by mprtcz on 2016-12-12.
 */
@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {

    private final
    GameService gameService;

    @Autowired
    public TicTacToeController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/create")
    public ResponseEntity createGame(Principal principal) {
        ServerMessages result = gameService.createTicTacToeGame(principal);
        return result.convertToResponseEntity();
    }

    @RequestMapping(value  = "/move", method = RequestMethod.PATCH)
    public ResponseEntity makeAMove(@RequestBody String coordinates, Principal principal) {
        ServerMessages result = gameService.findGameAndInsertMove(principal, coordinates);
        return result.convertToResponseEntity();
    }

    @RequestMapping("/{gameHost}/join")
    public ResponseEntity joinGame(@PathVariable String gameHost, Principal principal) {
        ServerMessages result = gameService.joinGame(gameHost, principal);
        return result.convertToResponseEntity();
    }

    @RequestMapping("/{gameHost}/game")
    public ResponseEntity getGameUpdate(@PathVariable String gameHost, Principal principal) {
        Game ticTacToeGame = gameService.getGameUpdate(gameHost, principal);
        if(ticTacToeGame == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticTacToeGame, HttpStatus.OK);
    }
}
