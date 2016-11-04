package com.mprtcz.tictactoeultimate.service;

import com.mprtcz.tictactoeultimate.mapper.UserMapper;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Azet on 2016-11-03.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public void saveUser(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
    }
}
