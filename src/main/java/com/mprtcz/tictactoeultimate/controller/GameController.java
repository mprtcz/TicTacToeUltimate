package com.mprtcz.tictactoeultimate.controller;

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
        gameService.createGame(principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value  = "/move", method = RequestMethod.PATCH)
    public ResponseEntity makeAMove(@RequestBody String coordinates, Principal principal) {
        String result = gameService.findGameAndInsertMove(principal, coordinates);
        if(result.equals("Symbol inserted") || result.contains("Winner is")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/{gameHost}/join")
    public ResponseEntity joinGame(@PathVariable String gameHost, Principal principal) {
        String result = gameService.joinGame(gameHost, principal);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping("/")
    public ResponseEntity getGamesList() {
        List<Game> gamesList = gameService.getGamesList();
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }
}
