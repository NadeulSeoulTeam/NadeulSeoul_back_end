package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.Data;

@Data
public class FollowDto {
    private Long followeeSeq;
    private String nickname;
    private String emoji;

    public FollowDto(Long followeeSeq, String nickname, String emoji) {
        this.followeeSeq = followeeSeq;
        this.nickname = nickname;
        this.emoji = emoji;
    }
}
