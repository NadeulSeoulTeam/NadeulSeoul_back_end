package com.alzal.nadeulseoulbackend.domain.stores.exception;

import com.alzal.nadeulseoulbackend.global.exception.EntityNotFoundException;

public class StoreInfoNotFoundException extends EntityNotFoundException {
    public StoreInfoNotFoundException(String message) {
        super(message);
    }
}
