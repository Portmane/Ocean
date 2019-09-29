package frameworks.spring.springBoot.tasks.springBootCapabilities.io.javabrains.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @RequestMapping("/hello")
    public String sayHi() {
        return "Hi";
    }
}
