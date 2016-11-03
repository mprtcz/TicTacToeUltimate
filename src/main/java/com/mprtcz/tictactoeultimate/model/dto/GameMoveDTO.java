package com.mprtcz.tictactoeultimate.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Azet on 2016-11-03.
 */
@Getter
@Setter
public class GameMoveDTO {

    private Long id;

    private String field;

    private String symbol;

    private LocalDateTime dateTime;
}
