package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException() {
        super(ErrorStatusEnum.ENTITY_NOT_FOUND);
    }
}