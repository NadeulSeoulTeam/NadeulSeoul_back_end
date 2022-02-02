package com.alzal.nadeulseoulbackend.global.common;

import lombok.Data;

@Data
public class ErrorResponse {
    private ErrorStatusEnum status;
    private String message;
    private Object data;

    public ErrorResponse() {
        this.status = null;
        this.data = null;
        this.message = null;
    }
}
