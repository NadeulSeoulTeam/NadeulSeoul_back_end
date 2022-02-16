package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class NotUniqueNicknameException extends CustomException {

    public NotUniqueNicknameException(ErrorStatusEnum errorStatusEnum) {
        super(errorStatusEnum);
    }

    public NotUniqueNicknameException(ErrorStatusEnum errorStatusEnum, String message) {
        super(errorStatusEnum, message);
    }
}
