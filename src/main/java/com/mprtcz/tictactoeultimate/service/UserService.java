package com.mprtcz.tictactoeultimate.service;

import com.mprtcz.tictactoeultimate.mapper.UserMapper;
import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Azet on 2016-11-03.
 */
@Service("userService")
public class UserService {

    private final
    UserRepository userRepository;

    private final
    UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void saveUser(UserDTO userDTO) {
        userRepository.save(userMapper.toEntity(userDTO));
    }

    public User findBySSO(String ssoId) {
        Iterable<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getSsoId().equals(ssoId)) {
                return user;
            }
        }
        return null;
    }
}
