package com.mprtcz.tictactoeultimate.configuration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * Created by mprtcz on 2016-11-06.
 */
@RestController
@RequestMapping("/api/")
public class DefaultController {

    @RequestMapping("/hello/{name}")
    public ResponseEntity getHelloName(@PathVariable String name) {
        return new ResponseEntity<>("Hello, " + name, HttpStatus.OK);
    }

    @RequestMapping("/hello")
    public ResponseEntity getHello() {
        return new ResponseEntity<>("Hello, unknown entity\n" + LocalDateTime.now(), HttpStatus.OK);
    }

    @RequestMapping("/principal")
    public ResponseEntity getPrincipal(Principal principal) {
        System.out.println(principal.toString());
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }
}