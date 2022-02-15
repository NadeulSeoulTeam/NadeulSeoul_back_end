package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class MyCurationBookmarkException extends CustomException {
    public MyCurationBookmarkException(String message) {
        super(ErrorStatusEnum.MY_CURATION_BOOKMARK_EXCEPTION ,message);
    }
}
