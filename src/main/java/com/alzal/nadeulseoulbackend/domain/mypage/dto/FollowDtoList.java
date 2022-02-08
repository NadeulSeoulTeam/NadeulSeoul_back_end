package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FollowDtoList {
    private List<FollowDto> followDtoList;
}
