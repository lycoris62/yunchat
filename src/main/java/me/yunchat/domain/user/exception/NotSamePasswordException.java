package me.yunchat.domain.user.exception;

import lombok.Getter;

@Getter
public class NotSamePasswordException extends RuntimeException {

    private final UserErrorCode errorCode;
    private final String detailMessage;

    public NotSamePasswordException(UserErrorCode errorCode) {
        super(errorCode.getStatusMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getStatusMessage();
    }
}
