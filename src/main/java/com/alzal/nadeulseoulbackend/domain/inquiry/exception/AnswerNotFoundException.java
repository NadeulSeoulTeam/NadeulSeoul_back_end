package com.alzal.nadeulseoulbackend.domain.inquiry.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class AnswerNotFoundException extends CustomException {
    public AnswerNotFoundException(String message) {
        super(ErrorStatusEnum.BAD_REQUEST, message);
    }
}
