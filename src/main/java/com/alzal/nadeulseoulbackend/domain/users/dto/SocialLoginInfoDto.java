package com.alzal.nadeulseoulbackend.domain.users.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
public class SocialLoginInfoDto {
    private String clientId;
    private String scope;

    @Builder
    public SocialLoginInfoDto(String clientId, String scope) {
        this.clientId = clientId;
        this.scope = scope;
    }
}
