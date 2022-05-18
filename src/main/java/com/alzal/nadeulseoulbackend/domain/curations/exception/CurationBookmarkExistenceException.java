package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CurationBookmarkExistenceException extends CustomException {
    public CurationBookmarkExistenceException(String message) {
        super(StatusEnum.CURATIONBOOKMARK_EXISTENCE, message);
    }
}
