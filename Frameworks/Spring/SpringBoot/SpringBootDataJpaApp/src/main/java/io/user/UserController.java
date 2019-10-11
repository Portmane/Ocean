package io.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService topicService;


    //Get requests.
    @RequestMapping("/topics")
    public List<User> getAllTopics() {
        return topicService.getUserList();
    }

//    @RequestMapping("/topics/{id}")
//    public User getTopic(@PathVariable String id) {
//        return topicService.getUser(id);
//    }



    //Post request.
    @RequestMapping(method = RequestMethod.POST, value = "/topics")
    public void addTopic(@RequestBody User user) {
        topicService.addUser(user);
    }


//    //Update request.
//    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{id}")
//    public void updateTopic(@RequestBody User user, @PathVariable String id) {
//        topicService.updateUser(id, user);
//    }


//    //Delete request.
//    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{id}")
//    public void deleteTopic(@PathVariable String id) {
//        topicService.deleteUser(id);
//    }
}
