package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CannotDeleteUserTokenInRedisException extends CustomException {

    public CannotDeleteUserTokenInRedisException(String message) {
        super(StatusEnum.CANNOT_DELETE_USER_TOKEN, message);
    }
}
