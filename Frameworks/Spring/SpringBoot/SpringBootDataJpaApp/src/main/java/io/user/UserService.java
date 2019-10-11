package io.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private List<User> userList = new ArrayList<>(Arrays.asList(new User("spring", "Spring Framework", "Spring Framework Description"),
                new User("java", "Core Java", "Core Java Description"),
                new User("javascript", "JavaScript", "JavaScript Description")));

    //Get methods.
    public List<User> getUserList() {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
        .forEach(users::add);
        return users;
    }
//    public User getUser(String id) {
//        return userList.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//    }

    //Add method.
    public void addUser(User user) {
        userRepository.save(user);
    }

//    //Update method.
//    public void updateUser(String id, User user) {
//        for (int i = 0; i < userList.size(); i++) {
//            User t = userList.get(i);
//            if (t.getId().equals(id)) {
//                userList.set(i, user);
//                return;
//            }
//
//        }
//    }

    //Delete method.
//    public void deleteUser(String id) {
//        userList.removeIf(t -> t.getId().equals(id));
//    }
}