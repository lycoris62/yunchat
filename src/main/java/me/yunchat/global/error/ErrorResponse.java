package me.yunchat.global.error;

import lombok.Builder;
import me.yunchat.domain.user.exception.UserErrorCode;

@Builder
public class ErrorResponse {

    private UserErrorCode status;
    private String statusMessage;
}
