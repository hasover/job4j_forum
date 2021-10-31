package ru.job4j.forum.repository;

import ru.job4j.forum.model.Topic;

import java.util.Collection;

public interface TopicStore {
    Collection<Topic> findAll();

    Topic save(Topic topic);

    Topic findById(int id);
}
