package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDto {

    private Long userSeq;
    private String nickname;
    private String emoji;

    @Builder
    public UserInfoDto(Long userSeq, String nickname, String emoji) {
        this.userSeq = userSeq;
        this.nickname = nickname;
        this.emoji = emoji;
    }

    public static UserInfoDto fromEntity(User user) {
        return UserInfoDto.builder()
                .userSeq(user.getUserSeq())
                .nickname(user.getNickname())
                .emoji(user.getEmoji())
                .build();
    }
}
