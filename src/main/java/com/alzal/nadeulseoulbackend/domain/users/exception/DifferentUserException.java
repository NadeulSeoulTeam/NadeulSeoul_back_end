package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class DifferentUserException extends CustomException {
    public DifferentUserException(String message) {
        super(StatusEnum.User_Not_Found, message);
    }
}
