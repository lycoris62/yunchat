package me.yunchat.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode {
    NOT_SAME_PASSWORD("확인 비밀번호가 다릅니다."),
    NO_USER_NICKNAME("유저 닉네임이 존재하지 않습니다.");

    private final String statusMessage;
}
