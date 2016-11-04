package com.mprtcz.tictactoeultimate.repository;

import com.mprtcz.tictactoeultimate.model.GameRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Azet on 2016-11-03.
 */
@Transactional
public interface GameRecordRepository extends CrudRepository<GameRecord, Long> {
}