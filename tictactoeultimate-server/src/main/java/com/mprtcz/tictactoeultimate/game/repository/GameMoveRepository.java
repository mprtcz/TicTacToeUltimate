package com.mprtcz.tictactoeultimate.game.repository;

import com.mprtcz.tictactoeultimate.game.model.GameMove;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Transactional
public interface GameMoveRepository extends CrudRepository<GameMove, Long> {
}