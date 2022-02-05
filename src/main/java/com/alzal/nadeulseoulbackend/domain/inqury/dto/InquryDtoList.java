package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel("문의 사항 목록")
public class InquryDtoList {
    private List<InquryDto> inquryDtoList;
}
