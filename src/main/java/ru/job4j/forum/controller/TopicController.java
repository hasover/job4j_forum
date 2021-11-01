package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AccessService;
import ru.job4j.forum.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopicController {
    private TopicService topicService;
    private AccessService accessService;

    public TopicController(TopicService topicService, AccessService accessService) {
        this.topicService = topicService;
        this.accessService = accessService;
    }

    @PostMapping("/topic")
    public String topic(@ModelAttribute Topic topic, HttpServletRequest request) {
        String userName =  SecurityContextHolder.getContext().getAuthentication().getName();
        topic.setUser(accessService.findUserByUsername(userName));
        topicService.saveOrUpdateTopic(topic);
        return "redirect:/";
    }

    @GetMapping("/topic")
    public String topic(@RequestParam(name = "topicId", required = false) Integer id, Model model,
                        HttpServletRequest request) {
        if (id != null) {
            Topic topic = topicService.getTopicById(id);
            model.addAttribute("topic", topic);
        }
        return "topic";
    }
}
