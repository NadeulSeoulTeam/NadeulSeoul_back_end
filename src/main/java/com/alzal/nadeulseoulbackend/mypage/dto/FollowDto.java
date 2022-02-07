package com.alzal.nadeulseoulbackend.mypage.dto;

import lombok.Data;

@Data
public class FollowDto {
    private Long followeeSeq;
    private Long followerSeq;
}
