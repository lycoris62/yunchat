package me.yunchat.web.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.user.application.UserService;
import me.yunchat.domain.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile/{nickname}")
    public String profile(@PathVariable String nickname, Model model) {
        User user = userService.findByNickname(nickname);
        model.addAttribute("user", user);
        return "profile";
    }
}
