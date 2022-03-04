package com.alzal.nadeulseoulbackend.domain.inquiry.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel("문의 사항 목록")
public class InquiryDtoList {
    private List<InquiryDto> inquiryDtoList;
}
