package ru.job4j.forum.repository;

import ru.job4j.forum.model.Topic;

import java.util.Collection;

public interface TopicStore {
    Collection<Topic> getAllTopics();

    void saveTopic(Topic topic);

    void updateTopic(Topic topic);

    Topic getTopicById(int id);
}
