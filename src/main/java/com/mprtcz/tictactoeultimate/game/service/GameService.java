package com.mprtcz.tictactoeultimate.game.service;

import com.mprtcz.tictactoeultimate.configuration.messages.ServerMessages;
import com.mprtcz.tictactoeultimate.game.model.Game;
import com.mprtcz.tictactoeultimate.game.model.GameMove;
import com.mprtcz.tictactoeultimate.game.model.GameRecord;
import com.mprtcz.tictactoeultimate.game.model.dto.GameRecordDTO;
import com.mprtcz.tictactoeultimate.game.model.mapper.GameRecordMapper;
import com.mprtcz.tictactoeultimate.game.repository.GameMoveRepository;
import com.mprtcz.tictactoeultimate.game.repository.GameRecordRepository;
import com.mprtcz.tictactoeultimate.user.model.User;
import com.mprtcz.tictactoeultimate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by mprtcz on 2016-11-22.
 */
@Service("gameService")
public class GameService {
    private static int CUSTOM_TABLE_SIZE = 3;
    private static List<Game> gamesList = new ArrayList<>();

    private final
    GameRecordRepository gameRecordRepository;

    private final GameRecordMapper gameRecordMapper;

    private final UserService userService;

    private final GameMoveRepository gameMoveRepository;

    @Autowired
    public GameService(GameRecordRepository gameRecordRepository, UserService userService, GameMoveRepository gameMoveRepository, GameRecordMapper gameRecordMapper) {
        this.gameRecordRepository = gameRecordRepository;
        this.userService = userService;
        this.gameMoveRepository = gameMoveRepository;
        this.gameRecordMapper = gameRecordMapper;
    }

    public ServerMessages createGame(Principal principal) {
        Game existingGameWithHost = findGameByHost(principal.getName());
        if (existingGameWithHost != null) {
            gamesList.remove(existingGameWithHost);
        }

        Game game = new Game(CUSTOM_TABLE_SIZE, principal, -1L);
        GameRecord gameRecord = createGameRecord(game);
        game.setGameId(gameRecord.getId());
        gamesList.add(game);
        return new ServerMessages(ServerMessages.ServerMessageEnum.GAME_CREATED);
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public ServerMessages findGameAndInsertMove(Principal principal, String gameMove) {
        for (Game g : filterUnresolvedGames()) {
            if (g.canAMoveBeMade(principal)) {
                ServerMessages serverMessages = g.makeAMove(principal.getName(), gameMove);
                saveMoveIfSuccessful(gameMove, g, serverMessages);
                return serverMessages;
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
                    updateGameRecordWithSecondPlayer(g, principal);
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

    private GameRecord createGameRecord(Game game) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setDateTime(LocalDateTime.now());
        gameRecord.setPlayerOne(userService.findBySSO(game.getGameHost()));
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }

    private void updateGameRecordWithSecondPlayer(Game g, Principal principal) {
        GameRecord gameRecord = findGameRecordById(g.getGameId());
        User user = userService.findBySSO(principal.getName());
        if (user != null && gameRecord != null) {
            gameRecord.setPlayerTwo(user);
        }
        gameRecordRepository.save(gameRecord);
    }

    private GameRecord findGameRecordById(Long id) {
        for (GameRecord gameRecord :
                gameRecordRepository.findAll()) {
            if (Objects.equals(gameRecord.getId(), id)) {
                return gameRecord;
            }
        }
        return null;
    }

    private void saveMoveIfSuccessful(String move, Game g, ServerMessages serverMessages) {
        if (serverMessages.getMessageEnum().equals(ServerMessages.ServerMessageEnum.SUCCESSFUL_MOVE) ||
                serverMessages.getMessageEnum().equals(ServerMessages.ServerMessageEnum.GAME_IS_WON)) {
            GameMove gameMove = new GameMove();
            gameMove.setDateTime(LocalDateTime.now());
            gameMove.setField(move);
            gameMove.setGameRecord(findGameRecordById(g.getGameId()));
            gameMove.setSymbol(g.getSymbolAtCoordinates(move));
            gameMoveRepository.save(gameMove);
        }
    }

    public List<GameRecordDTO> filterGameRecordDTOsByUser(String ssoId) {
        List<GameRecordDTO> gameRecordDTOList = new ArrayList<>();
        for (GameRecord gameRecord :
                filterGamesByUser(ssoId)) {
            gameRecordDTOList.add(gameRecordMapper.toDTO(gameRecord));
        }
        return gameRecordDTOList;
    }

    public List<GameRecord> filterGamesByUser(String ssoId) {
        List<GameRecord> gameRecordsList = getAllGameRecordsList();
        List<GameRecord> gameRecordsResultList = new ArrayList<>();
        for (GameRecord gameRecord : gameRecordsList) {
            if (gameRecord.getPlayerOne().getSsoId().equals(ssoId)
                    || gameRecord.getPlayerTwo().getSsoId().equals(ssoId)) {
                gameRecordsResultList.add(gameRecord);
            }
        }
        return gameRecordsResultList;
    }

    private List<GameRecord> getAllGameRecordsList() {
        Iterable<GameRecord> gameRecords = gameRecordRepository.findAll();
        List<GameRecord> gameRecordsList = new ArrayList<>();
        for (GameRecord gameRecord:
             gameRecords) {
            gameRecordsList.add(gameRecord);
        }
        return gameRecordsList;
    }

    public List<GameRecordDTO> getUserGames(String ssoId) {
        return filterGameRecordDTOsByUser(ssoId);
    }
}
