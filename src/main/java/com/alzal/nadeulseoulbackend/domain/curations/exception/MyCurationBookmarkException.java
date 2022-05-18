package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class MyCurationBookmarkException extends CustomException {
    public MyCurationBookmarkException(String message) {
        super(StatusEnum.MY_CURATION_BOOKMARK_EXCEPTION, message);
    }
}
