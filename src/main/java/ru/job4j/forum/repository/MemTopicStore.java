package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Topic;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemTopicStore implements TopicStore {
    private Map<Integer, Topic> topics = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public Collection<Topic> findAll() {
        return topics.values();
    }

    @Override
    public Topic save(Topic topic) {
        if (topic.getId() == 0) {
            return saveTopic(topic);
        } else {
            return updateTopic(topic);
        }
    }

    private Topic saveTopic(Topic topic) {
        int id = counter.incrementAndGet();
        topic.setId(id);
        topics.put(id, topic);
        return topic;
    }

    private Topic updateTopic(Topic topic) {
       Topic topicInStore = topics.get(topic.getId());
       topicInStore.setDescription(topic.getDescription());
       topicInStore.setName(topic.getName());
       return topic;
    }

    public Topic findById(int id) {
        return topics.get(id);
    }

}
