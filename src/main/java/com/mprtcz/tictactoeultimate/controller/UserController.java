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
 * Created by Azet on 2016-11-04.
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

    @RequestMapping("/user")
    public ResponseEntity user(Principal user) {
        if (user != null) {
            System.out.println("user = " + user);
            UserDTO userDTO = userService.getUserDTOBySsoId(user.getName());
            if (userDTO != null) {
                userDTO.setRole(null);
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity validateNewUser(@RequestBody User user) {
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
        List<UserDTO> users = userService.getAllUserDTOs();
        return new ResponseEntity(users, HttpStatus.OK);
    }
}
