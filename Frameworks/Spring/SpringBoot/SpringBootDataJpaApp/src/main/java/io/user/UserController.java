package io.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //Get requests.
    @RequestMapping("/users")
    public List<User> getAllTopics() {
        return userService.getUserList();
    }

    @RequestMapping("/users/{id}")
    public User getTopic(@PathVariable String id) {
        return userService.getUser(id);
    }



    //Post request.
    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addTopic(@RequestBody User user) {
        userService.addUser(user);
    }


    //Update request.
    @RequestMapping(method = RequestMethod.PUT, value = "/users/{id}")
    public void updateTopic(@RequestBody User user, @PathVariable String id) {
        userService.updateUser(id, user);
    }


    //Delete request.
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteTopic(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
