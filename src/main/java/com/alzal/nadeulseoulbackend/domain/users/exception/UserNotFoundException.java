package com.alzal.nadeulseoulbackend.domain.users.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class UserNotFoundException extends CustomException {

    public UserNotFoundException(String message) {
        super(ErrorStatusEnum.User_Not_Found,message);
    }
}
