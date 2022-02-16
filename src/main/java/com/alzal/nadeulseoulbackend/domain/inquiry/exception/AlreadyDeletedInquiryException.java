package com.alzal.nadeulseoulbackend.domain.inquiry.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class AlreadyDeletedInquiryException extends CustomException {
    public AlreadyDeletedInquiryException(String message) {
        super(ErrorStatusEnum.BAD_REQUEST, message);
    }
}
