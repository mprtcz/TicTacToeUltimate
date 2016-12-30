package com.mprtcz.tictactoeultimate.user.service;

import com.mprtcz.tictactoeultimate.configuration.security.RolesExtractor;
import com.mprtcz.tictactoeultimate.user.model.User;
import com.mprtcz.tictactoeultimate.user.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.user.model.mapper.UserMapper;
import com.mprtcz.tictactoeultimate.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
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
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void setRoleAndSaveUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(encryptPassword(user.getPassword()));
        user.setSsoId(user.getSsoId().toLowerCase());
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
        if(isAdminOrRootUser(editedUser, principal)) {
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

    public boolean isAdminOrRootUser(String ssoId, Principal principal) {
        User user = findBySSO(ssoId);
        return isAdminOrRootUser(user, principal);
    }

    private boolean isAdminOrRootUser(User user, Principal principal) {
        if(user == null) {
            return false;
        }
        boolean isAdmin = RolesExtractor.isAdmin();
        boolean isRootUser = principal.getName().equals(user.getSsoId());

        return isAdmin || isRootUser;
    }

    public boolean userExists(String ssoId) {
        return findBySSO(ssoId) != null;
    }

    List<String> convertSsoIdsToNickNames(Iterable<String> ssoIdList) {
        List<String> nicksList = new ArrayList<>();
        for(String ssoId: ssoIdList) {
            nicksList.add(findBySSO(ssoId).getNickname());
        }
        return nicksList;
    }
}