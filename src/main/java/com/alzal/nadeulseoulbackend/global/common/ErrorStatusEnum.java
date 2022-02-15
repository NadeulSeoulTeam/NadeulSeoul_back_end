package com.alzal.nadeulseoulbackend.global.common;

import lombok.Getter;

@Getter
public enum  ErrorStatusEnum {

    // common
    BAD_REQUEST(400, "BAD REQUEST"),

    NOT_FOUND(404, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR"),
    ENTITY_NOT_FOUND(400, "ENTITY NOT FOUND"),


    // user
    User_Not_Found(400,"유저를 찾을 수 없습니다."),
    INVALID_TOKEN(400,"비정상적인 토큰입니다."),
    NULL_TOKEN(400,"토큰값이 비어있습니다."),
    EMAIL_DUPLICATION(400, "DUPLICATED EMAIL"),
    NICKNAME_DUPLICATION(409,"DUPLICATED NICKNAME"),
    ALREADY_LOGGED_IN_USER(403,"이미 로그인된 사용자입니다."),
    CANNOT_DELETE_USER_TOKEN(400,"유저의 토큰을 삭제할 수 없습니다."),

    //curation
    CURATION_NOT_FOUND(400, "CURATION NOT FOUND"),

    //comment
    COMMENT_NOT_FOUND(400, "COMMENT NOT FOUND"),
    DUPLICATED_NICKNAME(400,"중복된 닉네임입니다."),

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
