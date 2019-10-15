package io.pages.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //Get all users requests.
    @RequestMapping("/groups/{id}/users")
    public List<User> getAllUsers(@PathVariable String id) {
        return userService.getAllUsers(id);
    }

    //Get user request.
    @RequestMapping("/groups/{groupId}/users/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }



    //Post request.
    @RequestMapping(method = RequestMethod.POST, value = "/groups/{groupId}/users")
    public void addUser(@RequestBody User user, @PathVariable String groupId) {
        user.setGroups(groupId);
        userService.addUser(user);
    }


    //Update request.
    @RequestMapping(method = RequestMethod.PUT, value = "/groups/{groupId}/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String groupId) {
        user.setGroups(groupId);
        userService.updateUser(user);
    }


    //Delete request.
    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/{groupId}/users/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
