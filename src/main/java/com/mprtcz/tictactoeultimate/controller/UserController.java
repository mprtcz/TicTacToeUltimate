package com.mprtcz.tictactoeultimate.controller;

import com.mprtcz.tictactoeultimate.model.User;
import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.service.UserService;
import com.mprtcz.tictactoeultimate.validator.NewUserValidator;
import com.mprtcz.tictactoeultimate.validator.UserConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-04.
 */
@RestController
public class UserController {

    private final
    UserService userService;

    private final
    NewUserValidator newUserValidator;

    @Autowired
    public UserController(UserService userService, NewUserValidator newUserValidator) {
        this.userService = userService;
        this.newUserValidator = newUserValidator;
    }

    @RequestMapping("/profile")
    public ResponseEntity profile(Principal user) {
        if (user != null) {
            UserDTO userDTO = userService.getUserDTOBySsoId(user.getName());
            if (userDTO != null) {
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.DELETE)
    public ResponseEntity deleteProfile(Principal user) {
        if (user != null) {
                userService.removeUser(user.getName());
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity validateAndSaveNewUser(@RequestBody User user) {
        List<UserConstraintViolation> violationsList = newUserValidator.validateUser(user);
        if (violationsList.size() > 0) {
            return new ResponseEntity(violationsList, HttpStatus.BAD_REQUEST);
        } else {
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/users")
    public ResponseEntity getAllUsers() {
        List<UserDTO> users = userService.getAllUserDTOsByPermission();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    public ResponseEntity editProfile(@RequestBody User user, Principal principal) {
        User loggedInUser = userService.findBySSO(principal.getName());
        if(!loggedInUser.getRole().equals("ROLE_ADMIN")) {
            if(!loggedInUser.getSsoId().equals(principal.getName())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        //userService.
        return null;
    }
}
