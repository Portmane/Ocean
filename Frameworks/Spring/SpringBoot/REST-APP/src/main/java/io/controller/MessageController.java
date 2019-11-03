package io.controller;

import io.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("messages")
public class MessageController {
    private int counter = 4;


    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{put("id", "1"); put("text", "First message"); }});
        add(new HashMap<String, String>() {{put("id", "2"); put("text", "Second message"); }});
        add(new HashMap<String, String>() {{put("id", "3"); put("text", "Third message"); }});
    }};



    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return messages.stream().filter(message -> message.get("id").equals(id)).findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PatchMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);

        return message;
    }

//    @PutMapping
//    public Map<String, String> update(@RequestBody Map<String, String> meesage) {
//
//    }
}
