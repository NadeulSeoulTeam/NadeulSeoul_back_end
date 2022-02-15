package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class CurationNotFoundException extends EntityNotFoundException {

    public CurationNotFoundException(String name) {
        super(name+" 존재하지 않습니다.");
    }
}