package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class NotUniqueNicknameException extends CustomException {
    public NotUniqueNicknameException(StatusEnum errorStatusEnum, String message) {
        super(errorStatusEnum, message);
    }
}
