package io.controller;

import io.domain.Role;
import io.domain.User;
import io.repos.UsrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class SignupController {
    @Autowired                                      // Give us instance(singleton) of the bean.
            UsrRepository usrRepository;                  // Variable which store UsrRepository interface instance(singleton).

    @GetMapping("/signup")                          // Get mapping to "/signup" path.
    public String signupAsUser() {
        return "mustache/signup/signup";
    }



    @PostMapping("/signup")                         // POST mapping to "/signup" path.
    public String addNewUser(User newUser, Map<String, Object> model) {
        User userFromDB = usrRepository.findByUsername(newUser.getUsername());
        if (userFromDB != null) {                         // Condition is it unique user.
            model.put("message", "User exists!");
            return "mustache/signup/signup";
        }

        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        usrRepository.save(newUser);

        return "redirect:/login";
    }
}
