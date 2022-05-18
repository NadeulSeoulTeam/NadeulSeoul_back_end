package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class DuplicatedNicknameException extends CustomException {

    public DuplicatedNicknameException(String message) {
        super(StatusEnum.NICKNAME_DUPLICATION, message);
    }
}
