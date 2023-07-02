package me.yunchat.domain.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginErrorCode {
    NOT_SAME_PASSWORD("확인 비밀번호가 다릅니다.");

    private final String statusMessage;
}
