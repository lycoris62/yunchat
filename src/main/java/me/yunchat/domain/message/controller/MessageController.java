package me.yunchat.domain.message.controller;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.message.application.MessageService;
import me.yunchat.domain.message.dto.ChatRequestDto;
import me.yunchat.domain.message.dto.ChatResponseDto;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat/{channelName}")
    @SendTo("/topic/chat/{channelName}")
    public ChatResponseDto chat(@DestinationVariable String channelName, ChatRequestDto message) {
        if (!channelName.equals(message.getChannelName())) {
            throw new IllegalArgumentException("요청 채널명과 전송 채널명의 불일치");
        }
        return messageService.addMessage(message);
    }
}
