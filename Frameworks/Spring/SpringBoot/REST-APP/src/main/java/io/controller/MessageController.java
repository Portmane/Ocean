package io.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.domain.Message;
import io.domain.Views;
import io.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("messages")
public class MessageController {
    private final MessageRepo messageRepo;
    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }




    @GetMapping
    @JsonView(Views.IdAndText.class)
    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getMessage(@PathVariable("id") Message messageToGet) {
        return messageToGet;
    }



    @PostMapping
    public Message createMessage(@RequestBody Message messageToAdd) {
        messageToAdd.setCreationDate(LocalDateTime.now());
        return messageRepo.save(messageToAdd);
    }

    @PutMapping("{id}")
    public Message updateMessage(
            @PathVariable("id") Message messageFromDatabase,
            @RequestBody Message messageFromUser) {
        BeanUtils.copyProperties(messageFromUser, messageFromDatabase, "id");

        return messageRepo.save(messageFromDatabase);
    }


    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message messageToDelete) {
        messageRepo.delete(messageToDelete);
    }
}
