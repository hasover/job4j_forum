package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MemPostStore implements PostStore {
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger();

    public void savePost(Post post) {
        int id = counter.incrementAndGet();
        post.setId(id);
        posts.put(id, post);
    }

    public void updatePost(Post post) {
        Post postInStore = posts.get(post.getId());
        postInStore.setDescription(post.getDescription());
    }

    public Post getPostById(int id) {
        return posts.get(id);
    }
}
