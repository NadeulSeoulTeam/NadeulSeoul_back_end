package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupInfoDto {
    private String nickname;
    private String emoji;

    @Builder
    public SignupInfoDto(String nickname, String emoji) {
        this.nickname = nickname;
        this.emoji = emoji;
    }

}
