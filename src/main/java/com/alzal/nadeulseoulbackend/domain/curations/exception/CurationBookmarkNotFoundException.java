package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class CurationBookmarkNotFoundException extends EntityNotFoundException {
    public CurationBookmarkNotFoundException(String message) {
        super(message);
    }
}
