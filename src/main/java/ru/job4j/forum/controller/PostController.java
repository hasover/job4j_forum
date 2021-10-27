package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
    private TopicService topicService;
    private PostService postService;

    public PostController(TopicService topicService, PostService postService) {
        this.topicService = topicService;
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String topicPosts(@RequestParam("topicId") Integer topicId, Model model) {
        model.addAttribute("posts", postService.getAllTopicPosts(topicId));
        model.addAttribute("topic", topicService.getTopicById(topicId));
        return "topic-posts";
    }

    @GetMapping("/post")
    public String postPage(@RequestParam(name = "postId", required = false) Integer id,
                           Model model) {
        if (id != null) {
            model.addAttribute("post", postService.getPostById(id));
        }
        return "post";
    }

    @PostMapping("/post")
    public String savePost(@ModelAttribute Post post,
                           @RequestParam("topicId") Integer topicId, HttpServletRequest request) {
        post.setUser((User) request.getSession().getAttribute("user"));
        boolean isNewPost = post.getId() == 0;
        postService.saveOrUpdatePost(post);
        if (isNewPost) {
            topicService.addPostToTopic(post, topicId);
        }
        return "redirect:/posts?topicId=" + topicId;
    }
}
