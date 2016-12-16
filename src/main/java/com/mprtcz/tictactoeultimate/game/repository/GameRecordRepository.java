package com.mprtcz.tictactoeultimate.game.repository;

import com.mprtcz.tictactoeultimate.game.model.GameRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Transactional
public interface GameRecordRepository extends CrudRepository<GameRecord, Long> {
}