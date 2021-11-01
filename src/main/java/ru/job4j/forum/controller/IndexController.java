package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.AccessService;
import ru.job4j.forum.service.TopicService;

@Controller
public class IndexController {
    private final TopicService topicService;
    private final AccessService accessService;

    public IndexController(TopicService topicService, AccessService accessService) {
        this.topicService = topicService;
        this.accessService = accessService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("topics", topicService.getAllTopics());
        model.addAttribute("user", accessService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()));
        return "index";
    }
}
