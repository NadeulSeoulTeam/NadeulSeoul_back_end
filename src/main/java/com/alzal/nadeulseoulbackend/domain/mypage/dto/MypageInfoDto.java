package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class MypageInfoDto {
    private Long userSeq;
    private String nickName;
    private String emoji;
    private Integer followeeCount;
    private Integer followerCount;
}
