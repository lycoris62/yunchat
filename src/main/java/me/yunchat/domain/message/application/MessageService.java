package me.yunchat.domain.message.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.dao.ChannelRepository;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.message.dao.MessageRepository;
import me.yunchat.domain.message.domain.Message;
import me.yunchat.domain.message.dto.ChatRequestDto;
import me.yunchat.domain.message.dto.ChatResponseDto;
import me.yunchat.domain.user.dao.UserRepository;
import me.yunchat.domain.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;

    public ChatResponseDto addMessage(ChatRequestDto chatRequestDto) {

        User user = userRepository.findByNickname(chatRequestDto.getNickname())
                .orElseThrow(() -> new IllegalArgumentException("닉네임 불읾치"));
        Channel channel = channelRepository.findByChannelName(chatRequestDto.getChannelName())
                .orElseThrow(() -> new IllegalArgumentException("채널명 불일치"));

        Message savedMessage = messageRepository.save(Message.builder()
                .content(chatRequestDto.getMessage())
                .user(user)
                .channel(channel)
                .build());

        return ChatResponseDto.builder()
                .message(savedMessage.getContent())
                .nickname(user.getNickname())
                .channelName(channel.getChannelName())
                .sendTime(savedMessage.getCreatedAt())
                .build();
    }
}
