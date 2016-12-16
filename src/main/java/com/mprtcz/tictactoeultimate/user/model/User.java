package com.mprtcz.tictactoeultimate.user.model;

import com.mprtcz.tictactoeultimate.game.model.GameRecord;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-01.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "username cannot be empty")
    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;

    @NotEmpty(message = "password cannot be empty")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty(message = "nickname cannot be empty")
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @NotEmpty(message = "email cannot be empty")
    @Column(name = "EMAIL", nullable = false ,unique=true)
    private String email;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @OneToMany(mappedBy = "playerOne")
    List<GameRecord> gamesHistoryAsO = new ArrayList<>();

    @OneToMany(mappedBy = "playerTwo")
    List<GameRecord> gamesHistoryAsX = new ArrayList<>();
}