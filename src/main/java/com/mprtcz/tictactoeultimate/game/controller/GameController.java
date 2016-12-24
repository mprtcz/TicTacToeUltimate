package com.mprtcz.tictactoeultimate.game.controller;

import com.mprtcz.tictactoeultimate.game.model.dto.GameRecordDTO;
import com.mprtcz.tictactoeultimate.game.model.interfaces.Game;
import com.mprtcz.tictactoeultimate.game.service.GameService;
import com.mprtcz.tictactoeultimate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by mprtcz on 2016-12-10.
 */
@RestController
@RequestMapping("/games")
public class GameController {

    private final
    GameService gameService;

    private final
    UserService userService;

    @Autowired
    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public ResponseEntity getGamesList() {
        List<Game> gamesList = gameService.filterUnresolvedGames();
        return new ResponseEntity<>(gamesList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{ssoId}")
    public ResponseEntity getUsersGames(@PathVariable String ssoId, Principal principal) {
        if(!userService.userExists(ssoId)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if(!userService.isAdminOrRootUser(ssoId, principal)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        List<GameRecordDTO> gameRecordDTOList = gameService.getUserGames(ssoId);
        return new ResponseEntity<>(gameRecordDTOList,HttpStatus.OK);
    }

//    public ResponseEntity getAllRunningGames() {
//
//    }
}
