package com.alzal.nadeulseoulbackend.domain.inquiry.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@ApiModel("문의사항 목록 정보")
public class InquiryDto {
    private Long questionSeq;
    private String questionTitle;
    private LocalDateTime questionDate;

    public InquiryDto(Long questionSeq, String questionTitle, LocalDateTime questionDate) {
        this.questionSeq = questionSeq;
        this.questionTitle = questionTitle;
        this.questionDate = questionDate;
    }
}
