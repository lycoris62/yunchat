package me.yunchat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProfileInfoDto {

    private String nickname;
    private LocalDateTime lastLoginTime;
}
