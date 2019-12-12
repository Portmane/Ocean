package io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MessageController {

    @GetMapping
    public String main(Map<String, Object> model) {
        model.put("some", "Hello me!!!");
        return "mustache/main";
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "WORLD") String name,
                        Map<String, Object> model) {
        model.put("name", name);
        return "mustache/index";
    }
}
