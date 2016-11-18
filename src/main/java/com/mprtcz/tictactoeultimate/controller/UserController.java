package com.mprtcz.tictactoeultimate.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/user")
    public Principal user(Principal user) {
        System.out.println("user = " + user);
        return user;
    }

    @Autowired
    private SessionRegistry sessionRegistry;

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
