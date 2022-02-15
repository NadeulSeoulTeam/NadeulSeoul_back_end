package com.alzal.nadeulseoulbackend.domain.inquiry.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@ApiModel("문의 사항 목록")
public class InquiryDtoList {
    private List<InquiryDto> inquiryDtoList;
}
