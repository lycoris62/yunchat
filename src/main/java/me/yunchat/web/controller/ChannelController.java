package me.yunchat.web.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.application.EnterChannelService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChannelController {

    private final EnterChannelService enterChannelService;

    @GetMapping("/channel/{channelName}")
    public String channel(@PathVariable String channelName, Model model, Authentication authentication) {

        String channel = enterChannelService.enter(channelName);

        model.addAttribute("channel", channel);
        model.addAttribute("nickname", authentication.getName());

        return "channel";
    }
}
