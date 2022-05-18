package com.alzal.nadeulseoulbackend.global.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonPropertyOrder({"status", "message", "data"})
public class Response {
    private StatusEnum status;
    private String message;
    private Object data;

    public Response(String message) {
        this.status = StatusEnum.OK;
        this.message = message;
        this.data = null;
    }

    public Response(String message, Object data) {
        this.status = StatusEnum.OK;
        this.message = message;
        this.data = data;
    }

    public Response(StatusEnum status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }
}
