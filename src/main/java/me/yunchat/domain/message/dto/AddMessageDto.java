package me.yunchat.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.message.domain.Message;
import me.yunchat.domain.user.domain.User;

@Getter
@Setter
@AllArgsConstructor
public class AddMessageDto {

    private String content;
    private User user;
    private Channel channel;

    public Message toEntity() {
        return Message.builder()
                .content(content)
                .user(user)
                .channel(channel)
                .build();
    }
}
