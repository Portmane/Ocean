package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // HOME controller for Shield module.
public class HomeController {
    @GetMapping                     // On any get request from the user, call this method.
    public String master() {
        return "HELLo";             // Data of return.
    }
}
