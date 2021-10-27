package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemTopicStore implements TopicStore {
    private Map<Integer, Topic> topics = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public Collection<Topic> getAllTopics() {
        return topics.values();
    }

    public void saveTopic(Topic topic) {
        int id = counter.incrementAndGet();
        topic.setId(id);
        topics.put(id, topic);
    }

    public void updateTopic(Topic topic) {
       Topic topicInStore = topics.get(topic.getId());
       topicInStore.setDescription(topic.getDescription());
       topicInStore.setName(topic.getName());
    }

    public Topic getTopicById(int id) {
        return topics.get(id);
    }

}
