package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final StatusEnum statusEnum;
    private final String message;

    public CustomException(StatusEnum errorStatusEnum, String message) {
        this.statusEnum = errorStatusEnum;
        this.message = message;
    }
}
