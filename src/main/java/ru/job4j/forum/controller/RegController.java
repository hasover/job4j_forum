package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AccessService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegController {
    private AccessService accessService;

    public RegController(AccessService accessService) {
        this.accessService = accessService;
    }

    @PostMapping("/reg")
    public String register(@ModelAttribute User user, Model model,
                           HttpServletRequest request, HttpServletResponse response) {
        try {
            accessService.saveUser(user);
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            model.addAttribute("errorMessage", "Пользователь существует.");
            return "reg";
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
