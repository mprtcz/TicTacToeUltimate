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

import java.security.Principal;
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

    public void setRoleAndSaveUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(encryptPassword(user.getPassword()));
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

    public void checkEditingPermissions(User editedUser, Principal principal) {
        User user = findBySSO(editedUser.getSsoId());
        if(user == null) {
            return;
        }
        if(principal.getName().equals(editedUser.getSsoId()) || RolesExtractor.isAdmin()) {
            saveEditedUser(user, editedUser);
        }
    }

    private void saveEditedUser(User user, User editedUser) {
        if(editedUser.getNickname() != null && !editedUser.getNickname().equals("")) {
            user.setNickname(editedUser.getNickname());
        }
        if(editedUser.getEmail() != null && !editedUser.getEmail().equals("")) {
            user.setEmail(editedUser.getEmail());
        }
        if(editedUser.getPassword() != null && !editedUser.getPassword().equals("")) {
            user.setPassword(encryptPassword(editedUser.getPassword()));
        }
        if(editedUser.getRole() != null && !editedUser.getRole().equals("")) {
            if(RolesExtractor.isAdmin()) {
                user.setRole(editedUser.getRole());
            }
        }
        userRepository.save(user);
    }

    private String encryptPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
