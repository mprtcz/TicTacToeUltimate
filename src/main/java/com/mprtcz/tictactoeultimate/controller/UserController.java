package com.mprtcz.tictactoeultimate.controller;

import com.mprtcz.tictactoeultimate.model.dto.UserDTO;
import com.mprtcz.tictactoeultimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
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
    SessionRegistry sessionRegistry;

    @Autowired
    public UserController(UserService userService, SessionRegistry sessionRegistry) {
        this.userService = userService;
        this.sessionRegistry = sessionRegistry;
    }

    @RequestMapping("/user")
    public ResponseEntity user(Principal user) {
        System.out.println("user = " + user);
        UserDTO userDTO = userService.getUserDTOBySssId(user.getName());
        if(userDTO!=null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/users")
    public void listLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        System.out.println("All principals number " +allPrincipals.size());
        System.out.println("allPrincipals.toString() = " + allPrincipals.toString());

        for (final Object principal : allPrincipals) {
            if (principal instanceof UserDetails) {
                final UserDetails user = (UserDetails) principal;

                List<SessionInformation> activeUserSessions =
                        sessionRegistry.getAllSessions(principal,
                                /* includeExpiredSessions */ false); // Should not return null;

                if (!activeUserSessions.isEmpty()) {
                    // Do something with user
                    System.out.println(user);
                }
            }
        }
    }
}
