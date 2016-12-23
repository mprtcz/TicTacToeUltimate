package com.mprtcz.tictactoeultimate.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mprtcz on 2016-12-23.
 */
@Service
public class UserSessionRegistry {

    private final SessionRegistry sessionRegistry;

    @Autowired
    public UserSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    private List<UserDetails> getOnlineUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<UserDetails> usersDetailsList = new ArrayList<>();

        for (Object principal : principals) {
            if (principal instanceof UserDetails) {
                if (!((UserDetails) principal).getUsername().equals(userName)) {
                    for (SessionInformation sess : sessionRegistry.getAllSessions(principal, false)) {
                        if (!sess.isExpired()) {
                            usersDetailsList.add((UserDetails) sess.getPrincipal());
                        }
                    }
                }
            }
        }
        return usersDetailsList;
    }

    public List<String> getOnlineUserNames() {
        List<String> usernames = new ArrayList<>();
        for (UserDetails userDetails :
                getOnlineUserDetails()) {
            usernames.add(userDetails.getUsername());
        }
        return usernames;
    }
}
