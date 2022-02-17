package com.alzal.nadeulseoulbackend.domain.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class FollowDtoList {
    private List<FollowDto> followDtoList;
}
