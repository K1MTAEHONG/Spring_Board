package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인 페이지
    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_ADMIM')")

    // 회원 등록 페이지
    @GetMapping("/users/join")
    public String joinPage(UserModel user, Model model) {
        model.addAttribute("user", user);
        return "join";
    }

    // 회원 등록 처리
    @PostMapping("/users")
    public String postUser(UserModel user, Model model) {
        try {
            userService.insertUser(user);
        }catch (DataIntegrityViolationException ex) {
            model.addAttribute("errMessage","사용할수 없는 ID입니다.");
            model.addAttribute("user", user);
            return "join";
        }
        return "redirect:/";
    }


    // 회원 정보 수정 페이지

    // 회원 정보 수정 처리
}