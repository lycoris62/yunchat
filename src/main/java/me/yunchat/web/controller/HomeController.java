package me.yunchat.web.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.dao.ChannelRepository;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.user.application.UserService;
import me.yunchat.domain.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final ChannelRepository channelRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/profile/{nickname}")
    public String profile(@PathVariable String nickname, Model model) {
        User user = userService.findByNickname(nickname);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/channel/{channelName}")
    public String channel(@PathVariable String channelName, Model model, Authentication authentication) {
        Channel channel = channelRepository.findByChannelName(channelName)
                .orElseGet(() -> channelRepository.save(Channel.builder().channelName(channelName).build()));

        model.addAttribute("channel", channel);
        model.addAttribute("nickname", authentication.getName());
        return "channel";
    }
}
