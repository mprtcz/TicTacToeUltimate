package com.mprtcz.tictactoeultimate.user.controller;

import com.mprtcz.tictactoeultimate.user.model.User;
import com.mprtcz.tictactoeultimate.user.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.user.service.UserService;
import com.mprtcz.tictactoeultimate.user.validator.NewUserValidator;
import com.mprtcz.tictactoeultimate.user.validator.UserConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by mprtcz on 2016-11-04.
 */
@RequestMapping("api/users")
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity validateAndSaveNewUser(@RequestBody User user) {
        List<UserConstraintViolation> violationsList = newUserValidator.validateUser(user);
        if (violationsList.size() > 0) {
            return new ResponseEntity(violationsList, HttpStatus.BAD_REQUEST);
        } else {
            userService.setRoleAndSaveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("")
    public ResponseEntity getAllUsers() {
        List<UserDTO> users = userService.getAllUserDTOsByPermission();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PATCH)
    public ResponseEntity editProfile(@RequestBody User user, Principal principal) {
        User loggedInUser = userService.findBySSO(principal.getName());
        if (!loggedInUser.getRole().equals("ROLE_ADMIN")) {
            if (!loggedInUser.getSsoId().equals(principal.getName())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        userService.checkEditingPermissions(user, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{ssoId}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@PathVariable String ssoId, Principal principal) {
        User loggedInUser = userService.findBySSO(principal.getName());
        User userToDelete = userService.findBySSO(ssoId);
        if(userToDelete == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (!loggedInUser.getRole().equals("ROLE_ADMIN")) {
            if (!userToDelete.getSsoId().equals(principal.getName())) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }
        }
        userService.removeUser(userToDelete.getSsoId());
        return new ResponseEntity(HttpStatus.OK);
    }
}
