package com.mprtcz.tictactoeultimate.configuration.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by mprtcz on 2016-12-05.
 */
public class RolesExtractor {

    public static boolean isAdmin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal.toString().equals("anonymousUser")) {
            return false;
        }
        User user = (User) principal;
        boolean result = user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return result;
    }
}