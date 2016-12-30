package com.mprtcz.tictactoeultimate.user.model.mapper;

import com.mprtcz.tictactoeultimate.user.model.User;
import com.mprtcz.tictactoeultimate.user.model.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by mprtcz on 2016-11-03.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    List<UserDTO> toDTOs(List<User> users);
}
