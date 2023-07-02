package me.yunchat.domain.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yunchat.domain.web.application.LoginService;
import me.yunchat.domain.web.dto.SignUpRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("signUpRequestDto", new SignUpRequestDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("signUpRequestDto") SignUpRequestDto request) {
        loginService.signup(request);
        return "redirect:/login";
    }
}