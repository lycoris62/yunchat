package me.yunchat.web.dto;

import lombok.Getter;
import lombok.Setter;
import me.yunchat.domain.user.dto.SignUpDto;

@Getter
@Setter
public class SignUpRequestDto {

    private String nickname;
    private String password;
    private String confirmPassword;

    public SignUpDto toSignUpDto() {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("두 비밀번호가 같지 않습니다.");
        }
        return new SignUpDto(nickname, password, confirmPassword);
    }
}
