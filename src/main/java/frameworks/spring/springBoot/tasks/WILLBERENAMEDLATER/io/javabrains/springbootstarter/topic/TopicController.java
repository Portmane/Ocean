package frameworks.spring.springBoot.tasks.WILLBERENAMEDLATER.io.javabrains.springbootstarter.topic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class TopicController {

    @RequestMapping("/topics")
    public List<Topic> getAllTopics() {
        return Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
                new Topic("java", "Core Java", "Core Java Description"),
                new Topic("javascript", "JavaScript", "JavaScript Description"));
    }
}
