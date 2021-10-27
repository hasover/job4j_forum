package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.TopicStore;
import java.util.Collection;

@Service
public class TopicService {
    private TopicStore topicStore;

    public TopicService(TopicStore topicStore) {
        this.topicStore = topicStore;
    }

    public void saveOrUpdateTopic(Topic topic) {
       if (topic.getId() == 0) {
           topicStore.saveTopic(topic);
       } else {
           topicStore.updateTopic(topic);
       }
    }

    public Collection<Topic> getAllTopics() {
        return topicStore.getAllTopics();
    }

    public Topic getTopicById(int id) {
        return topicStore.getTopicById(id);
    }

    public void addPostToTopic(Post post, int topicId) {
        topicStore.getTopicById(topicId).addPost(post);
    }
}
