package com.alzal.nadeulseoulbackend.domain.mypage.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
    public MemberNotFoundException(String message) {
        super(message);
    }
}