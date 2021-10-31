package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
       topicStore.save(topic);
    }

    public Collection<Topic> getAllTopics() {
        return topicStore.findAll();
    }

    public Topic getTopicById(int id) {
        return topicStore.findById(id);
    }

    @Transactional
    public void addPostToTopic(Post post, int topicId) {
        topicStore.findById(topicId).addPost(post);
    }
}
