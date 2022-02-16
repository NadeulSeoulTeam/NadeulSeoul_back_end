package com.alzal.nadeulseoulbackend.domain.inquiry.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class AnswerExistenceException extends CustomException {
    public AnswerExistenceException(String message) {
        super(ErrorStatusEnum.ANSWER_EXISTENCE, message);
    }
}
