package me.yunchat.domain.message.application;

import lombok.RequiredArgsConstructor;
import me.yunchat.domain.channel.application.ChannelService;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.message.dao.MessageRepository;
import me.yunchat.domain.message.domain.Message;
import me.yunchat.domain.message.dto.AddMessageDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChannelService channelService;

    public void addMessage(AddMessageDto addMessageDto) {
        Channel channel = addMessageDto.getChannel();
        Message savedMessage = messageRepository.save(addMessageDto.toEntity());
        channelService.updateLastChatTime(channel.getChannelName(), savedMessage.getCreatedAt());
    }
}
