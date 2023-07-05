package me.yunchat.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDto {

    private String nickname;
    private String password;
    private String confirmPassword;
}
