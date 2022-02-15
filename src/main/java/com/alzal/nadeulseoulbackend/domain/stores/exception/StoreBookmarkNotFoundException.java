package com.alzal.nadeulseoulbackend.domain.stores.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class StoreBookmarkNotFoundException extends EntityNotFoundException {
    public StoreBookmarkNotFoundException(String message) {
        super(message);
    }
}
