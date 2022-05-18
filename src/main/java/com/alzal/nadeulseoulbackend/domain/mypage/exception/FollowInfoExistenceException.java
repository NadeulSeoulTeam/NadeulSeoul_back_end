package com.alzal.nadeulseoulbackend.domain.mypage.exception;

import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.exception.CustomException;

public class FollowInfoExistenceException extends CustomException {
    public FollowInfoExistenceException(String message) {
        super(StatusEnum.FOLLOWINFO_EXISTENCE, message);
    }
}
