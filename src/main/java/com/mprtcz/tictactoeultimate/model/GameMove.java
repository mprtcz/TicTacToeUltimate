package com.mprtcz.tictactoeultimate.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Azet on 2016-11-01.
 */
@Getter
@Setter
@Table(name = "GAME_MOVES")
public class GameMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIELD")
    private String field;

    @Column(name = "SYMBOL")
    private String symbol;

    @ManyToOne
    @Column(name = "GAME_ID")
    private GameRecord gameRecord;

    @Column(name = "DATETIME")
    private LocalDateTime dateTime;
}
