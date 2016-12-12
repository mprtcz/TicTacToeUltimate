package com.mprtcz.tictactoeultimate.controller;

import com.mprtcz.tictactoeultimate.model.Game;
import com.mprtcz.tictactoeultimate.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mprtcz on 2016-12-10.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private final
    GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/")
    public ResponseEntity getGamesList() {
        List<Game> gamesList = gameService.getGamesList();
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }
}
