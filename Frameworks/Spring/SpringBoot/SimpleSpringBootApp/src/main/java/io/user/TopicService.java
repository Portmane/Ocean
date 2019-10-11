package io.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TopicService {

    private List<Topic> topicList = new ArrayList<>(Arrays.asList(new Topic("spring", "Spring Framework", "Spring Framework Description"),
                new Topic("java", "Core Java", "Core Java Description"),
                new Topic("javascript", "JavaScript", "JavaScript Description")));

    //Get methods.
    public List<Topic> getTopicList() {
        return topicList;
    }
    public Topic getTopic(String id) {
        return topicList.stream().filter(t -> t.getId().equals(id)).findFirst().get();
    }

    //Add method.
    public void addTopic(Topic topic) {
        topicList.add(topic);
    }

    //Update method.
    public void updateTopic(String id, Topic topic) {
        for (int i = 0; i < topicList.size(); i++) {
            Topic t = topicList.get(i);
            if (t.getId().equals(id)) {
                topicList.set(i, topic);
                return;
            }

        }
    }

    //Delete method.
    public void deleteTopic(String id) {
        topicList.removeIf(t -> t.getId().equals(id));
    }
}