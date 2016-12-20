package com.mprtcz.tictactoeultimate.game.model;

import com.mprtcz.tictactoeultimate.user.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-01.
 */
@Getter
@Setter
@Table(name = "GAME_RECORDS")
@Entity
public class GameRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "gameRecord")
    List<GameMove> movesList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PLAYER_ONE_ID")
    User playerOne;

    @ManyToOne
    @JoinColumn(name = "PLAYER_TWO_ID", nullable = true)
    User playerTwo;

    @Column(name = "CREATION_DATE")
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "GameRecord{" +
                "id=" + id +
                ", playerOneSSOID=" + playerOne.getSsoId() +
                ", dateTime=" + dateTime +
                '}';
    }
}
