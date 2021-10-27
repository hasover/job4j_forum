package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

public interface PostStore {
    void savePost(Post post);

    void updatePost(Post post);

    Post getPostById(int id);

}
