package com.mprtcz.tictactoeultimate.controller;

import com.mprtcz.tictactoeultimate.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.model.Game;
import com.mprtcz.tictactoeultimate.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by mprtcz on 2016-12-10.
 */
@RestController
@RequestMapping("/games/")
public class GameController {

    private final
    GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/create")
    public ResponseEntity createGame(Principal principal) {
        ServerMessages result = gameService.createGame(principal);
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

    @RequestMapping("/")
    public ResponseEntity getGamesList() {
        List<Game> gamesList = gameService.getGamesList();
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }
}
