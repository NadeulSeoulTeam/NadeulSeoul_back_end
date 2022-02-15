package com.alzal.nadeulseoulbackend.domain.statics.dto;

import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NadeulerDto {
    private Long userSeq;
    private String nickname;
    private String emoji;

    @Builder
    public NadeulerDto(Long userSeq, String nickname, String emoji) {
        this.userSeq = userSeq;
        this.nickname = nickname;
        this.emoji = emoji;
    }

    public static NadeulerDto fromEntity(User user) {
        return NadeulerDto.builder()
                .userSeq(user.getUserSeq())
                .nickname(user.getNickname())
                .emoji(user.getEmoji())
                .build();
    }
}
