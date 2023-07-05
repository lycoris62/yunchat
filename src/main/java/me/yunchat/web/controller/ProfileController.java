package me.yunchat.web.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.user.application.ProfileInfoService;
import me.yunchat.domain.user.dto.ProfileInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileInfoService profileInfoService;

    @GetMapping("/profile/{nickname}")
    public String profile(@PathVariable String nickname, Model model) {
        ProfileInfoDto userProfileInfo = profileInfoService.getUserProfile(nickname);
        model.addAttribute("user", userProfileInfo);
        return "profile";
    }
}
