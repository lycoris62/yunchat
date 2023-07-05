package me.yunchat.domain.message.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequestDto {

    private String message;
    private String nickname;
    private String channelName;
}
