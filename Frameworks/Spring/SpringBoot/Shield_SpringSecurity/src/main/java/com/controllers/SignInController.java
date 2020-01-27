package com.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController                                 // HOME controller for Shield module.
public class SignInController {
    @GetMapping("/")                            // On any GET request from the user, call this method.
    public String indexPage() {
        return "index";             // DATA of return.
    }

    @GetMapping("/user")                        // On "/user" GET request.
    public String userPage() {
        return "<h1>USER</h1>";     // DATA of return.
    }

    @GetMapping("/admin")                       // On "/admin" GET request.
    public String adminPage() {
        return "<h1>ADMIN</h1>";     // DATA of return.
    }
}
