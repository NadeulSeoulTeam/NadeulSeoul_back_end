package com.alzal.nadeulseoulbackend.domain.inquiry.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class AnswerNotFoundException extends CustomException {
    public AnswerNotFoundException(String message) {
        super(StatusEnum.BAD_REQUEST, message);
    }
}
