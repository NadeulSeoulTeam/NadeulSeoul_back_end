package com.alzal.nadeulseoulbackend.domain.tag.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class TagNotFoundException extends EntityNotFoundException {
    public TagNotFoundException(String name) {
        super(name + " 존재하지 않습니다");
    }
}
