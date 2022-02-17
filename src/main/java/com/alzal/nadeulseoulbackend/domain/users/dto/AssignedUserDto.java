package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class AssignedUserDto {
    Long userSeq;
    String nickname;
    String role;
    String emoji;
    Integer followeeCount;
    Integer followerCount;

    @Builder
    public AssignedUserDto(Long userSeq, String emoji, String nickname, String role, Integer followeeCount, Integer followerCount) {
        this.userSeq = userSeq;
        this.emoji = emoji;
        this.nickname = nickname;
        this.role = role;
        this.followeeCount = followeeCount;
        this.followerCount = followerCount;
    }
}
