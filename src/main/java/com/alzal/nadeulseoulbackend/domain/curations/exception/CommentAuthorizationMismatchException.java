package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CommentAuthorizationMismatchException extends CustomException {
    public CommentAuthorizationMismatchException(String msg){
        super(ErrorStatusEnum.USER_MISMATCH, msg);
    }
}
