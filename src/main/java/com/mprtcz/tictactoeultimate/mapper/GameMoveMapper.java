package com.mprtcz.tictactoeultimate.mapper;

import com.mprtcz.tictactoeultimate.model.GameMove;
import com.mprtcz.tictactoeultimate.model.dto.GameMoveDTO;
import org.mapstruct.Mapper;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Mapper(componentModel = "spring")
public interface GameMoveMapper {

    GameMoveDTO toDTO(GameMove gameMove);

    GameMove toEntity(GameMoveDTO gameMoveDTO);
}
