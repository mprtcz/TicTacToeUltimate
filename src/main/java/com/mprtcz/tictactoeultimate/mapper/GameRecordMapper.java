package com.mprtcz.tictactoeultimate.mapper;

import com.mprtcz.tictactoeultimate.model.GameRecord;
import com.mprtcz.tictactoeultimate.model.dto.GameRecordDTO;
import org.mapstruct.Mapper;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GameMoveMapper.class})
public interface GameRecordMapper {

    GameRecordDTO toDTO(GameRecord gameRecord);

    GameRecord toEntity(GameRecordDTO gameRecordDTO);
}
