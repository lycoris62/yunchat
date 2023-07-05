package me.yunchat.domain.message.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ChatResponseDto {

    private String message;
    private String nickname;
    private String channelName;
    private LocalDateTime sendTime;
}
