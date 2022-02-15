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
    EMAIL_DUPLICATION(400, "DUPLICATED EMAIL"),

    //curation
    CURATION_NOT_FOUND(400, "CURATION NOT FOUND"),

    //comment
    COMMENT_NOT_FOUND(400, "COMMENT NOT FOUND"),

    // inquiry
    ANSWER_EXISTENCE(400,"ANSWER EXISTENCE"),

    // tag
    CODE_EXCEPTION(400, "CODE EXCEPTION")
    ;



    int code;
    String message;

    ErrorStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
