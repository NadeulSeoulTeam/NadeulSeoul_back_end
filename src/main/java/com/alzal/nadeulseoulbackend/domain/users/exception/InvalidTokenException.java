package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class InvalidTokenException extends CustomException {

    public InvalidTokenException(String message) {
        super(StatusEnum.INVALID_TOKEN, message);
    }
}
