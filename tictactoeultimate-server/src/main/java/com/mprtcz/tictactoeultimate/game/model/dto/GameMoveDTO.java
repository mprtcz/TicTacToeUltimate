package com.mprtcz.tictactoeultimate.game.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Getter
@Setter
public class GameMoveDTO {

    private Long id;

    private String field;

    private String symbol;

    private LocalDateTime dateTime;
}
