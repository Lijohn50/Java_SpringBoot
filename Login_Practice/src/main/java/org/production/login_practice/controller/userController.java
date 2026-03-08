package org.production.login_practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {

    @GetMapping("/")
    public String welcome(){

        return "hello welcome home";
    }
}
