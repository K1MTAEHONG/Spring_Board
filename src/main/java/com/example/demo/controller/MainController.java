package com.example.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        return "redirect:/comments";
    }

    // MainController (또는 ErrorController)
    @GetMapping("/errors/access-denied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("message", "요청 페이지에 접근할 권한이 없습니다.");
        return "error";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/test")
    public String empty() {
        return "pure";
    }
}