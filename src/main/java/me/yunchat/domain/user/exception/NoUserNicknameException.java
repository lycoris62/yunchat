package me.yunchat.domain.user.exception;

import lombok.Getter;

@Getter
public class NoUserNicknameException extends RuntimeException {

    private final UserErrorCode errorCode;
    private final String detailMessage;

    public NoUserNicknameException(UserErrorCode errorCode) {
        super(errorCode.getStatusMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getStatusMessage();
    }
}
