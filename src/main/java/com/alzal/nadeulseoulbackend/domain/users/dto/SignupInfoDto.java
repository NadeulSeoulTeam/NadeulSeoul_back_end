package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Getter;

@Getter
public class SignupInfoDto {
    private final String nickname;
    private final String emoji;

    public SignupInfoDto(String nickname, String emoji) {
        this.nickname = nickname;
        this.emoji = emoji;
    }

}
