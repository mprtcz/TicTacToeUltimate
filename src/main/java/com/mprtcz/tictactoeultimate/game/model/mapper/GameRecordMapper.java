package com.mprtcz.tictactoeultimate.game.model.mapper;

import com.mprtcz.tictactoeultimate.game.model.GameRecord;
import com.mprtcz.tictactoeultimate.game.model.dto.GameRecordDTO;
import com.mprtcz.tictactoeultimate.user.model.mapper.UserMapper;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, GameMoveMapper.class})
public interface GameRecordMapper {

    GameRecordDTO toDTO(GameRecord gameRecord);

    GameRecord toEntity(GameRecordDTO gameRecordDTO);

    List<GameRecordDTO> toDTOs(List<GameRecord> gameRecords);
}
