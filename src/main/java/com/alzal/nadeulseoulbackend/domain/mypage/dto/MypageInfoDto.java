package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MypageInfoDto {
    private Long memberSeq;
    private String nickName;
    private String emoji;
    private Long followeeCount;
    private Long followerCount;
}
