package io.pages.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;


    //Get requests.
    @RequestMapping("/groups")
    public List<Group> getAllTopics() {
        return groupService.getUserList();
    }

    @RequestMapping("/groups/{id}")
    public Group getTopic(@PathVariable String id) {
        return groupService.getUser(id);
    }



    //Post request.
    @RequestMapping(method = RequestMethod.POST, value = "/groups")
    public void addTopic(@RequestBody Group group) {
        groupService.addUser(group);
    }


    //Update request.
    @RequestMapping(method = RequestMethod.PUT, value = "/groups/{id}")
    public void updateTopic(@RequestBody Group group, @PathVariable String id) {
        groupService.updateUser(id, group);
    }


    //Delete request.
    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/{id}")
    public void deleteTopic(@PathVariable String id) {
        groupService.deleteUser(id);
    }
}
