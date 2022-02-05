package com.alzal.nadeulseoulbackend.global.common;

import lombok.Getter;

@Getter
public enum  ErrorStatusEnum {

    // common
    BAD_REQUEST(400, "BAD REQUEST"),
    NOT_FOUND(404, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR"),
    ENTITY_NOT_FOUND(400, "ENTITY NOT FOUND"),

    // member
    EMAIL_DUPLICATION(400, "DUPLICATED EMAIL")
    ;

    int code;
    String message;

    ErrorStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
