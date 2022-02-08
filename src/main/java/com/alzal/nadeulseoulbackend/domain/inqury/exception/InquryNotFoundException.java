package com.alzal.nadeulseoulbackend.domain.inqury.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class InquryNotFoundException extends EntityNotFoundException {
    public InquryNotFoundException(String message) {
        super(message + "존재하지 않습니다.");
    }
}
