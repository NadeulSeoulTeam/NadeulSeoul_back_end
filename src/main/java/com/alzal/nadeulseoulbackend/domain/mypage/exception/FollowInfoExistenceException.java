package com.alzal.nadeulseoulbackend.domain.mypage.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class FollowInfoExistenceException extends CustomException {
    public FollowInfoExistenceException(String message) {
        super(ErrorStatusEnum.FOLLOWINFO_EXISTENCE, message);
    }
}
