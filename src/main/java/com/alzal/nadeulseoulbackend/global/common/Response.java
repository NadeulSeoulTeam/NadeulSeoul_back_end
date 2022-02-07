package com.alzal.nadeulseoulbackend.global.common;

import lombok.Data;

@Data
public class Response {
    private StatusEnum status;
    private String message;
    private Object data;

    public Response() {
        this.status = StatusEnum.OK;
        this.data = null;
        this.message = null;
    }
}
