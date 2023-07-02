package me.yunchat.domain.channel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.yunchat.domain.channel.domain.Channel;
import me.yunchat.domain.user.domain.User;

@Getter
@Setter
@AllArgsConstructor
public class ChannelInOutDto {

    private Channel channel;
    private User user;
}
