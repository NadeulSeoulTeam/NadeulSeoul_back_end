package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCommentDto {

    private Long userSeq;
    private String nickname;
    private String emoji;

    @Builder
    public UserCommentDto(Long userSeq, String nickname, String emoji) {
        this.userSeq = userSeq;
        this.nickname = nickname;
        this.emoji = emoji;
    }

    public static UserCommentDto fromEntity(User user) {
        return UserCommentDto.builder()
                .userSeq(user.getUserSeq())
                .nickname(user.getNickName())
                .emoji(user.getEmoji())
                .build();
    }
}
