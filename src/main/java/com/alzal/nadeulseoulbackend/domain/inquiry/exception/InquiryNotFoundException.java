package com.alzal.nadeulseoulbackend.domain.inquiry.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class InquiryNotFoundException extends EntityNotFoundException {
    public InquiryNotFoundException(String message) {
        super(message + "존재하지 않습니다.");
    }
}
