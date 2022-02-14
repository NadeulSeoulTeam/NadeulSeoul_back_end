package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class DuplicatedNicknameException extends CustomException {

    public DuplicatedNicknameException(String message) {
        super(ErrorStatusEnum.DUPLICATED_NICKNAME,message);
    }
}
