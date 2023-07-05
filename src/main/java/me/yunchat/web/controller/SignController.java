package me.yunchat.web.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.user.application.SignUpService;
import me.yunchat.web.dto.SignUpRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignController {

    private final SignUpService signUpService;

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
        boolean isSignUp = signUpService.signup(request.toSignUpDto());
        return isSignUp ? "redirect:/login" : "/signup";
    }
}
