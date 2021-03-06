package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostStore;
import ru.job4j.forum.repository.TopicStore;

import java.util.Collection;

@Service
public class PostService {
    private PostStore postStore;
    private TopicStore topicStore;

    public PostService(PostStore postStore, TopicStore topicStore) {
        this.postStore = postStore;
        this.topicStore = topicStore;
    }

    public Collection<Post> getAllTopicPosts(int topicId) {
        return topicStore.findById(topicId).getPosts();
    }

    public void saveOrUpdatePost(Post post) {
        postStore.save(post);
    }

    public Post getPostById(int id) {
        return postStore.findById(id);
    }
}
