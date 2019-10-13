package io.pages.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    //Get methods.
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        groupRepository.findAll()
        .forEach(groups::add);
        return groups;
    }
    public Group getGroup(String id) {
        return groupRepository.findById(id).get();
    }

    //Add method.
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    //Update method.
    public void updateGroup(String id, Group group) {
        groupRepository.save(group);
    }

    //Delete method.
    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
}