package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CurationBookmarkExistenceException extends CustomException {
    public CurationBookmarkExistenceException(String message) {
        super(ErrorStatusEnum.CURATIONBOOKMARK_EXISTENCE, message);
    }
}
