package com.mprtcz.tictactoeultimate.service;

import com.mprtcz.tictactoeultimate.mapper.UserMapper;
import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.repository.UserRepository;
import com.mprtcz.tictactoeultimate.security.RolesExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by mprtcz on 2016-11-03.
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

    public void saveUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public User findBySSO(String ssoId) {
        Iterable<User> users = getAllUsers();
        for (User user : users) {
            if (user.getSsoId().equals(ssoId)) {
                return user;
            }
        }
        return null;
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDTO> getAllUserDTOsByPermission() {
        List<UserDTO> users = userMapper.toDTOs((List<User>) getAllUsers());
        if (RolesExtractor.isAdmin()) {
            return users;
        } else {
            ListIterator<UserDTO> listIterator = users.listIterator();
            while (listIterator.hasNext()) {
                if (listIterator.next().getRole().equals("ROLE_ADMIN")) {
                    listIterator.remove();
                }
            }
            return users;
        }
    }

    public UserDTO getUserDTOBySsoId(String ssoId) {
        User user = findBySSO(ssoId);
        return userMapper.toDTO(user);
    }

    public void removeUser(String username) {
        User user = findBySSO(username);
        System.out.println("Removing user: " + user.toString());
        userRepository.delete(user);
    }

    public void editUser(User editedUser) {
        User user = findBySSO(editedUser.getSsoId());
    }

}
