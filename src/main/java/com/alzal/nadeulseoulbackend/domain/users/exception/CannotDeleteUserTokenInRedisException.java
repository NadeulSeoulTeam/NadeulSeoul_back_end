package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CannotDeleteUserTokenInRedisException extends CustomException {

    public CannotDeleteUserTokenInRedisException(String message) {
        super(ErrorStatusEnum.CANNOT_DELETE_USER_TOKEN,message);
    }
}
