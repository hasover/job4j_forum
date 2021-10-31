package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

public interface PostStore {

    Post save(Post post);

    Post findById(int id);
}
