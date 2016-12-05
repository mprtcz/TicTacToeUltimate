package com.mprtcz.tictactoeultimate.repository;

import com.mprtcz.tictactoeultimate.model.GameMove;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Transactional
public interface GameMoveRepository extends CrudRepository<GameMove, Long> {
}