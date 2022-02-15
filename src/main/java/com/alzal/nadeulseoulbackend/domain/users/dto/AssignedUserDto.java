package com.alzal.nadeulseoulbackend.domain.users.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AssignedUserDto {
    Long userSeq;
    String nickname;
    String role;
    Integer followeeCount;
    Integer followerCount;

    @Builder
    public AssignedUserDto(Long userSeq, String nickname, String role, Integer followeeCount, Integer followerCount) {
        this.userSeq = userSeq;
        this.nickname = nickname;
        this.role = role;
        this.followeeCount = followeeCount;
        this.followerCount = followerCount;
    }
}
