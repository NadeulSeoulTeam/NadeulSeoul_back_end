package com.alzal.nadeulseoulbackend.domain.curations.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class CommentAuthorizationMismatchException extends CustomException {
    public CommentAuthorizationMismatchException(String msg){
        super(StatusEnum.USER_MISMATCH, msg);
    }
}
