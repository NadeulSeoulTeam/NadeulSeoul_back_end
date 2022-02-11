package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class SignupInfo {
    private String nickname;
    private String emoji;

    public SignupInfo(String nickname, String emoji) {
        this.nickname = nickname;
        this.emoji = emoji;
    }

}
