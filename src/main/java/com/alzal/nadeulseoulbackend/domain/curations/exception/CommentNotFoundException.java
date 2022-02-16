package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class CommentNotFoundException extends EntityNotFoundException {

    public CommentNotFoundException(String name) {
        super(name + " 존재하지 않습니다");
    }
}