package io.controller;

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
    @Autowired                                          // Give us instance(singleton) of the bean.
    private MessageRepository messageRepository;        // Variable which store MessageRepository interface instance(singleton).




    @GetMapping("/")                                         // Standard mapping for GET method.
    public String helloPage(Map<String, Object> model) {
        return "mustache/index";
    }


    @GetMapping("/login")                       // Get mapping to "/login" path.
    public String login() {
        return "mustache/login/login";
    }


    @GetMapping("/CM")                                  // Get mapping to "/CM" path.
    public String messagesPage(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();   // Getting all messages.

        model.put("messages", messages);                            // Updating of the model.

        return "mustache/CM/index";
    }

    @GetMapping("/hello")                               // Get mapping to "/hello" path.
    public String helloPage(@RequestParam(name = "name", required = false, defaultValue = "WORLD") String name,
                        Map<String, Object> model) {
        model.put("name", name);                        // Updating of the model.


        return "mustache/hello/index";
    }



    @PostMapping("/CM")                                        // Standard POST("/CM") mapping.
    public String addNewMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);       // Creation of the new instance(Entity).

        messageRepository.save(message);                // Saving the new instance.

        Iterable<Message> messages = messageRepository.findAll();   // Getting all messages.
        model.put("messages", messages);                            // Updating of the model.


        return "mustache/CM/index";
    }


    @PostMapping("/filterByTag")                             // Get mapping to "/filterByTag" path.
    public String filterByTag(@RequestParam String tagNameToFind, Map<String, Object> model) {
        Iterable<Message> messages;                                     // Variable where we are store messages.

        if (tagNameToFind != null && !tagNameToFind.isEmpty())          // Condition on not void of the parameter.
            messages = messageRepository.findByTag(tagNameToFind);      // Finding all messages with given tag name.
        else
            messages = messageRepository.findAll();                     // On void of the parameter return all messages.

        model.put("messages", messages);                                // Updating of the model.


        return "mustache/CM/index";
    }
}
