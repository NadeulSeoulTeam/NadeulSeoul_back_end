package com.alzal.nadeulseoulbackend.domain.mypage.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}