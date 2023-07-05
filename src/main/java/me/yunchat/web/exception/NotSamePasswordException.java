package me.yunchat.web.exception;

import lombok.Getter;

@Getter
public class NotSamePasswordException extends RuntimeException {

    private final LoginErrorCode errorCode;
    private final String detailMessage;

    public NotSamePasswordException(LoginErrorCode errorCode) {
        super(errorCode.getStatusMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getStatusMessage();
    }
}
