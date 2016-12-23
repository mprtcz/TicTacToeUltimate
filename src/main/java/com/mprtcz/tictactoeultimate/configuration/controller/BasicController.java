package com.mprtcz.tictactoeultimate.configuration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mprtcz on 2016-11-07.
 */
@Controller
public class BasicController {

    @RequestMapping({"/", "/greeting", "/register", "/profile", "/games", "/users", "/customlogin"})
    public String getIndex() {
        return "index.html";
    }
}
