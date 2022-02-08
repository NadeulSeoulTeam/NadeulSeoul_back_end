package com.alzal.nadeulseoulbackend.domain.mypage.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class FollowInfoNotFoundException extends EntityNotFoundException {
    public FollowInfoNotFoundException(String message) {
        super(message);
    }
}
