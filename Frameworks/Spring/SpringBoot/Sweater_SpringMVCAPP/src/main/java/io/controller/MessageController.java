package io.controller;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import io.domain.Message;
import io.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MessageController {
    @Autowired                                          // Give us
    private MessageRepository messageRepository;        // Variable which store MessageRepository interface instance.




    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);
        return "mustache/index";
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "WORLD") String name,
                        Map<String, Object> model) {
        model.put("name", name);
        return "mustache/main";
    }



    @PostMapping
    public String add(@RequestParam String text,@RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepository.save(message);


        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);


        return "mustache/index";
    }
}
