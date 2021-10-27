package ru.job4j.forum.controller;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.TopicService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(2)
public class EditAccessFilter implements Filter {
    private TopicService topicService;
    private PostService postService;

    public EditAccessFilter(TopicService topicService, PostService postService) {
        this.topicService = topicService;
        this.postService = postService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();

        if ((uri.endsWith("topic") || uri.endsWith("post")) && req.getMethod().equals("POST")) {
            String strId = servletRequest.getParameter("id");
            if (strId != null) {
                int id = Integer.parseInt(servletRequest.getParameter("id"));
                User sessionUser = (User) req.getSession().getAttribute("user");

                if (uri.endsWith("topic")) {
                    if (!topicService.getTopicById(id).getUser().equals(sessionUser)) {
                        resp.sendRedirect(".");
                        return;
                    }
                } else {
                    if (!postService.getPostById(id).getUser().equals(sessionUser)) {
                        resp.sendRedirect(".");
                        return;
                    }
                }
            }
        }
        filterChain.doFilter(req, resp);
    }
}
