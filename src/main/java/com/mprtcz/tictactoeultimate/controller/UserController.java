package com.mprtcz.tictactoeultimate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
}
