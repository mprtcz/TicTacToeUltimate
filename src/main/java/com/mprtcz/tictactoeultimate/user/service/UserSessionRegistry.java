package com.mprtcz.tictactoeultimate.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mprtcz on 2016-12-23.
 */
@Service
public class UserSessionRegistry {

    private final SessionRegistry sessionRegistry;

    private final
    UserService userService;

    @Autowired
    public UserSessionRegistry(SessionRegistry sessionRegistry, UserService userService) {
        this.sessionRegistry = sessionRegistry;
        this.userService = userService;
    }

    private List<UserDetails> getOnlineUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<UserDetails> usersDetailsList = new ArrayList<>();

        for (Object principal : principals) {
            if (principal instanceof UserDetails) {
                for (SessionInformation sess : sessionRegistry.getAllSessions(principal, false)) {
                    if (!sess.isExpired()) {
                        usersDetailsList.add((UserDetails) sess.getPrincipal());
                    }
                }
            }
        }
        return usersDetailsList;
    }

    public List<String> getOnlineUserNames() {
        Set<String> usernames = new HashSet<>();
        for (UserDetails userDetails :
                getOnlineUserDetails()) {
            usernames.add(userDetails.getUsername());
        }
        return userService.convertSsoIdsToNickNames(usernames);
    }
}