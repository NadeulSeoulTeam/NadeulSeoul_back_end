package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String message) {
        super(StatusEnum.ENTITY_NOT_FOUND, message);
    }
}