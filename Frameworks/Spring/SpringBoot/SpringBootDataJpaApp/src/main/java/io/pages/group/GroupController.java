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
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @RequestMapping("/groups/{id}")
    public Group getGroup(@PathVariable String id) {
        return groupService.getGroup(id);
    }



    //Post request.
    @RequestMapping(method = RequestMethod.POST, value = "/groups")
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
    }


    //Update request.
    @RequestMapping(method = RequestMethod.PUT, value = "/groups/{id}")
    public void updateGroup(@RequestBody Group group, @PathVariable String id) {
        groupService.updateGroup(id, group);
    }


    //Delete request.
    @RequestMapping(method = RequestMethod.DELETE, value = "/groups/{id}")
    public void deleteGroup(@PathVariable String id) {
        groupService.deleteGroup(id);
    }
}
