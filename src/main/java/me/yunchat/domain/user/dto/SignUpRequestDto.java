package me.yunchat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {

    private String nickname;
    private String password;
    private String confirmPassword;
}
