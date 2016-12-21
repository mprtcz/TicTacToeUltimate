package com.mprtcz.tictactoeultimate.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mprtcz on 2016-11-01.
 */
@Getter
@Setter
@Table(name = "GAME_MOVES")
@Entity
public class GameMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FIELD")
    private String field;

    @Column(name = "SYMBOL")
    private String symbol;

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    private GameRecord gameRecord;

    @Column(name = "CREATION_DATE")
    private LocalDateTime dateTime;
}
