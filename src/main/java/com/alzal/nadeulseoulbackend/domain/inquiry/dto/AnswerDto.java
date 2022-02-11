package com.alzal.nadeulseoulbackend.domain.inquiry.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel("문의 사항 답변 작성 및 수정")
public class AnswerDto {
    private Long questionSeq;
    private String answer;
}
