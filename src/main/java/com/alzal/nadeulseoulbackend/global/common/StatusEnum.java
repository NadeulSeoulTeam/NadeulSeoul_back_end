package com.alzal.nadeulseoulbackend.global.common;

public enum StatusEnum {

    OK(200, "OK");

    int code;
    String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}