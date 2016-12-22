package com.mprtcz.tictactoeultimate.game.model.mapper;

import com.mprtcz.tictactoeultimate.game.model.GameMove;
import com.mprtcz.tictactoeultimate.game.model.dto.GameMoveDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Mapper(componentModel = "spring")
public interface GameMoveMapper {

    GameMoveDTO toDTO(GameMove gameMove);

    GameMove toEntity(GameMoveDTO gameMoveDTO);

    List<GameMoveDTO> toMoveDTOs(List<GameMove> gameMove);
}
