package com.mprtcz.tictactoeultimate.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Created by mprtcz on 2016-12-05.
 */
public class RolesExtractor {

    public static boolean isAdmin() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}