package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowDto {
    private Long followeeSeq;
    private Long followerSeq;

    public FollowDto(Long followeeSeq, Long followerSeq) {
        this.followeeSeq = followeeSeq;
        this.followerSeq = followerSeq;
    }
}
