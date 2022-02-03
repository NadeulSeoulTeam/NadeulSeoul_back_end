package com.alzal.nadeulseoulbackend.global.common;

public enum  ErrorStatusEnum {

    // common
    BAD_REQUEST(400, "BAD REQUEST"),
    NOT_FOUND(404, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR"),

    // member
    EMAIL_DUPLICATION(400, "DUPLICATED EMAIL"),

    // tag
    CODE_NOT_EXISTS(400, "CODE NOT EXISTS")
    ;



    int code;
    String message;

    ErrorStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
