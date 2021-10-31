package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemPostStore implements PostStore {
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    private Post savePost(Post post) {
        int id = counter.incrementAndGet();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    private Post updatePost(Post post) {
        Post postInStore = posts.get(post.getId());
        postInStore.setDescription(post.getDescription());
        return post;
    }

    @Override
    public Post save(Post post) {
        if (post.getId() == 0) {
            return savePost(post);
        } else {
            return updatePost(post);
        }
    }

    public Post findById(int id) {
        return posts.get(id);
    }
}
