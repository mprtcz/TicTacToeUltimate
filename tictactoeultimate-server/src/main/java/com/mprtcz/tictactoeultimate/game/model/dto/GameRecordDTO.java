package com.mprtcz.tictactoeultimate.game.model.dto;

import com.mprtcz.tictactoeultimate.user.model.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Getter
@Setter
public class GameRecordDTO {

    Long id;

    UserDTO playerOne;

    UserDTO playerTwo;

    private LocalDateTime dateTime;

    List<GameMoveDTO> gameMovesList;
}
