package frameworks.spring.springBoot.tasks.WILLBERENAMEDLATER.io.javabrains.springbootstarter.topic;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class TopicService {

    private List<Topic> topicList = Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
                new Topic("java", "Core Java", "Core Java Description"),
                new Topic("javascript", "JavaScript", "JavaScript Description"));

    public List<Topic> getTopicList() {
        return topicList;
    }
}