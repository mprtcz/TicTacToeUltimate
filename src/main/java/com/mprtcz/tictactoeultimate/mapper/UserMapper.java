package com.mprtcz.tictactoeultimate.mapper;

import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Created by Azet on 2016-11-03.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}